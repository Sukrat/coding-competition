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
            regexs.set(parseInt(num), def)
        })

    const regexPattern = new RegExp(`^${build(0, regexs)}$`)
    console.log(regexPattern);

    const matches = sections[1].trim().split('\n')
        .filter(m => regexPattern.test(m))

    return matches.length
}

function build(nos: number, regex: Map<number, string>): string {
    if (!regex.has(nos)) {
        throw new Error()
    }
    const def = regex.get(nos) ?? ''
    if (def.includes('"')) {
        return def.split('"')[1]
    } else if (def.includes('|')) {
        const ors = def.split(" | ")
            .map(or => or.split(" ")
                .map(m => build(parseInt(m), regex))
                .join(""))
            .join("|")
        return `(${ors})`
    } else {
        return def.split(" ")
            .map(m => build(parseInt(m), regex)).join("")
    }
}
