import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/8/input",
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

    const visited = new Array<boolean>(lines.length)
    let i = 0
    let acc = 0
    while (visited[i] !== true) {
        const line = lines[i]
        const match = /^(nop|acc|jmp) ([+-][0-9]+)$/.exec(line)
        if (match === null) {
            throw new Error()
        }
        const op = match[1]
        const arg = parseInt(match[2])
        visited[i] = true
        if (op === 'acc') {
            acc += arg
            i += 1
        } else if (op === 'jmp') {
            i += arg
        } else {
            i += 1
        }
    }
    return acc
}
