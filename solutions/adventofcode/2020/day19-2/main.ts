import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/19/input",
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
    let sections = str.trim().split('\n\n')

    const regexs = new Map<number, string>()
    sections[0].trim().split('\n')
        .forEach(line => {
            const [num, def] = line.trim().split(': ')
            regexs.set(parseInt(num), def.trim())
        })

    regexs.set(8, '42 | 42 8')
    regexs.set(11, '42 31 | 42 11 31')

    const cache = new Map<number, string>()
    const regexPattern = new RegExp(`^${build(0, regexs, cache)}$`)

    const matches = sections[1].trim().split('\n')
        .filter(m => regexPattern.test(m))

    return matches.length
}

function build(nos: number, regex: Map<number, string>, cache: Map<number, string>, recurseLimit = 10): string {
    if (!regex.has(nos)) {
        throw new Error()
    }
    if (cache.has(nos)) {
        return cache.get(nos) ?? ''
    }
    if (recurseLimit === 0) {
        return ''
    }

    const def = regex.get(nos) ?? ''
    let result = ''
    if (def.includes('"')) {
        result = def.split('"')[1]
    } else if (def.includes('|')) {
        result = def.split(" | ")
            .map(or => or.split(" ")
                .map(m => build(parseInt(m), regex, cache, nos === parseInt(m) ? recurseLimit - 1 : recurseLimit))
                .join(""))
            .join("|")
        result = `(?:${result})`
    } else {
        result = def.split(" ")
            .map(m => build(parseInt(m), regex, cache)).join("")
    }
    cache.set(nos, result)
    return result
}
