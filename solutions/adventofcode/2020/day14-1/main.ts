import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/14/input",
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
    let lines = str.trim().split('\n')

    const memory = new Map<number, number>()
    let mask: string[] = []

    for (const [index, line] of lines.entries()) {
        if (line.startsWith("mask")) {
            let match = /^mask = (.*)$/.exec(line)
            if (match === null) {
                throw new Error()
            }
            mask = match[1].split('').reverse()
        } else {
            let match = /^mem\[([0-9]+)\] = (.*)$/.exec(line)
            if (match === null) {
                throw new Error()
            }
            const address = parseInt(match[1])
            const value = parseInt(match[2])

            const binary = value.toString(2).split('').reverse()

            const newValue = maskOperation(mask, binary)
            memory.set(address, newValue)
        }
    }

    return (Array.from(memory.values()).reduce((sum, curr) => {
        return sum + curr
    }, 0))
}

function maskOperation(mask: string[], value: string[]): number {
    let str: string[] = mask.map((m, index) => {
        return m === 'X' ? (value[index] ?? 0) : m
    })
    return parseInt(str.reverse().join(''), 2)
}
