import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/22/input",
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
    const sections = str.trim().split('\n\n')

    const p1 = sections[0].trim().split('\n')
        .slice(1)
        .map(m => parseInt(m))

    const p2 = sections[1].trim().split('\n')
        .slice(1)
        .map(m => parseInt(m))

    while (p1.length > 0 && p2.length > 0) {
        const c1 = p1.shift() ?? 0
        const c2 = p2.shift() ?? 0
        if (c1 > c2) {
            p1.push(c1, c2)
        } else {
            p2.push(c2, c1)
        }
    }

    const score = (p1.length > 0 ? p1 : p2)
        .reverse()
        .reduce((prev, curr, i) => prev + (curr * (i + 1)), 0)
    return score
}
