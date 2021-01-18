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
    const rules = new Map<string, [number, string][]>()
    const cache = new Map<string, number>()

    for (const line of lines) {
        if (line.length === 0) {
            continue
        }
        const [bag, contents] = extractColour(line)
        rules.set(bag, contents)
    }
    const total = totalBagItCanHold("shiny gold", rules, cache)
    return total
}

function extractColour(line: string): [string, [number, string][]] {
    let match = /^([a-z ]+) bags contain ([a-z,0-9 ]+).$/.exec(line)
    if (match === null) {
        throw new Error(line)
    }
    const mainBag = match[1]
    const bagContains = match[2]
    const contents: [number, string][] = []
    if (bagContains !== 'no other bags') {
        const bags = bagContains.split(', ')
        for (const bag of bags) {
            let match = /^([0-9]+) ([a-z ]+) (bag|bags)$/.exec(bag)
            if (match === null) {
                throw new Error(bag)
            } else {
                const nos = parseInt(match[1])
                const color = match[2]
                contents.push([nos, color])
            }
        }
    }
    return [mainBag, contents]
}

function totalBagItCanHold(
    bag: string,
    rules: Map<string, [number, string][]>,
    cache: Map<string, number>): number {
    if (cache.has(bag)) {
        return cache.get(bag) ?? 0
    }
    let contents = rules.get(bag) ?? []
    let total = 0
    for (const [nos, content] of contents) {
        const count = totalBagItCanHold(content, rules, cache)
        total += nos + (nos * count)
    }
    cache.set(bag, total)
    return total
}
