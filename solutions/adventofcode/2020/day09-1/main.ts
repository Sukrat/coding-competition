import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/9/input",
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
    const numbers = str.trim().split('\n')
        .map(m => parseInt(m))
    const preamble = 25

    const prevNumbers = new Set<number>(numbers.slice(0, preamble))
    for (let index = preamble; index < numbers.length; index++) {
        const target = numbers[index]
        if (!findPair(target, prevNumbers)) {
            return target
        }
        prevNumbers.delete(numbers[index - preamble])
        prevNumbers.add(target)
    }
    throw new Error()
}

function findPair(target: number, prev: Set<number>): boolean {
    for (const num of prev) {
        const need = target - num
        if (prev.has(need)) {
            return true
        }
    }
    return false
}
