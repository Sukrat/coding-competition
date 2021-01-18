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

function solve(str: string): number | undefined {
    const numbers = str.trim().split('\n')
        .map(m => parseInt(m))
    const preamble = 25

    const prevNumbers = new Set<number>(numbers.slice(0, preamble))
    for (let index = preamble; index < numbers.length; index++) {
        const target = numbers[index]
        if (!findPair(target, prevNumbers)) {
            return findContinuous(target, numbers)
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

function findContinuous(target: number, numbers: number[]): number {
    const cumulative = new Array<number>(numbers.length)

    for (const [index, num] of numbers.entries()) {
        cumulative[index] = num + (cumulative[index - 1] ?? 0)
    }
    for (const [i, sumA] of cumulative.entries()) {
        for (let j = i + 1; j < cumulative.length; j++) {
            const sumB = cumulative[j];
            if (sumB - sumA === target) {
                const ordered = numbers.slice(i + 1, j + 1)
                    .sort()
                return ordered[0] + ordered[ordered.length - 1]
            }
            if (sumB - sumA > target) {
                break
            }
        }
    }
    throw new Error()
}
