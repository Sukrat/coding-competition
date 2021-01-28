import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/21/input",
    {
        headers: {
            "cookie": `session=${process.env.ADVENTOFCODE};`
        }
    })
    .then(res => {
        return res.text()
    }).then(data => {
        const answer = solve(data)
        console.log(answer)
    })

type Menu = [string[], string[]][]

function solve(str: string): string {
    const lines = str.trim().split('\n')
    const menu: Menu = lines.map(line => {
        let match = /^([a-z ]+) \(contains ([a-z ,]+)\)$/.exec(line)
        if (match === null) {
            throw new Error()
        }
        return [match[1].trim().split(' '), match[2].trim().split(', ')]
    })

    const map = new Map<string, Set<string>>()
    for (const [ingredients, allergens] of menu) {
        const toSearch = new Set<string>(ingredients)
        for (const allergen of allergens) {
            let mayContain = map.get(allergen)
            if (mayContain) {
                mayContain = intersection(mayContain, toSearch)
            } else {
                mayContain = new Set<string>(toSearch)
            }
            map.set(allergen, mayContain)
        }
    }

    const allergens = Array.from(map.keys())
    while (allergens.length > 0) {
        const allergen = allergens.pop() ?? ''
        const ingredients = map.get(allergen) ?? new Set<string>()
        if (ingredients.size === 1) {
            const ingredient = Array.from(ingredients)[0]
            for (const [key, val] of map) {
                if (key === allergen) {
                    continue
                }
                val.delete(ingredient)
            }
        } else {
            allergens.unshift(allergen)
        }
    }

    return Array.from(map.entries())
        .sort((a, b) => a[0].localeCompare(b[0]))
        .flatMap(m => Array.from(m[1]))
        .join(',')
}

function intersection(setA: Set<string>, setB: Set<string>): Set<string> {
    const intersect = new Set<string>()
    for (const a of setA) {
        if (setB.has(a)) {
            intersect.add(a)
        }
    }
    return intersect
}

function subtract(setA: Set<string>, setB: Set<string>): Set<string> {
    const sub = new Set<string>(setA.values())
    for (const b of setB) {
        sub.delete(b)
    }
    return sub
}
