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
    let mask = ''

    for (const [index, line] of lines.entries()) {
        if (line.startsWith("mask")) {
            let match = /^mask = (.*)$/.exec(line)
            if (match === null) {
                throw new Error()
            }
            mask = match[1]
        } else {
            let match = /^mem\[([0-9]+)\] = (.*)$/.exec(line)
            if (match === null) {
                throw new Error()
            }
            const address = parseInt(match[1])
            const value = parseInt(match[2])

            const binary = address.toString(2).padStart(36, '0')

            const maskedAddress = updateOperation(memory, mask, binary)
            maskedAddress.forEach(m => memory.set(m, value))
        }
    }

    return (Array.from(memory.values()).reduce((sum, curr) => {
        return sum + curr
    }, 0))
}

function updateOperation(map: Map<number, number>, mask: string, binary: string): number[] {
    let arr = ['']
    for (const [i, m] of mask.split('').entries()) {
        if (m === '0') {
            arr = arr.map(a => a.concat(binary.charAt(i)))
        } else if (m === '1') {
            arr = arr.map(a => a.concat('1'))
        } else {
            let zeroes = arr.map(a => a.concat('0'))
            let ones = arr.map(a => a.concat('1'))
            arr = zeroes.concat(ones)
        }
    }
    return arr.map(m => parseInt(m, 2))
}
