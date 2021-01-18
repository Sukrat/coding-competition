import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/7/input",
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

function solve(str: string): number {
    const lines = str.split('\n')
    const rules = new Map<string, string[]>()
    const cache = new Map<string, boolean>()

    for (const line of lines) {
        if (line.length === 0) {
            continue
        }
        const [bag, contents] = extractColour(line)
        rules.set(bag, contents)
    }

    let sum = 0
    for (const bag of rules.keys()) {
        if (canHaveShinyGoldBag(bag, rules, cache)) {
            sum += 1
        }
    }
    return sum
}

function extractColour(line: string): [string, string[]] {
    let match = /^([a-z ]+) bags contain ([a-z,0-9 ]+).$/.exec(line)
    if (match === null) {
        throw new Error(line)
    }
    const mainBag = match[1]
    const bagContains = match[2]
    const contents: string[] = []
    if (bagContains !== 'no other bags') {
        const bags = bagContains.split(', ')
        for (const bag of bags) {
            let match = /^[0-9]+ ([a-z ]+) (bag|bags)$/.exec(bag)
            if (match === null) {
                throw new Error(bag)
            } else {
                const color = match[1]
                contents.push(color)
            }
        }
    }
    return [mainBag, contents]
}

function canHaveShinyGoldBag(
    bag: string,
    rules: Map<string, string[]>,
    cache: Map<string, boolean>): boolean {
    if (cache.has(bag)) {
        return cache.get(bag) ?? false
    }

    const contents = rules.get(bag) ?? []
    let hasShiny = false
    if (contents.includes("shiny gold")) {
        hasShiny = true
    } else {
        for (const content of contents) {
            hasShiny = hasShiny || canHaveShinyGoldBag(content, rules, cache)
        }
    }
    cache.set(bag, hasShiny)
    return hasShiny
}
