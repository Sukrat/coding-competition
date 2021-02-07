import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/10/input",
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
    const adapters = str.trim().split('\n')
        .map(m => parseInt(m))

    const sortedAdapters = adapters.sort((a, b) => a - b)

    const list = [0].concat(sortedAdapters)
    const cache = new Array<number>(sortedAdapters.length)

    const total = combinations(0, list, cache)

    return total
}

function combinations(pos: number, arr: number[], cache: number[]): number {
    let comb = 0
    if (cache[pos] > 0) {
        comb = cache[pos]
    } else if (pos === arr.length - 1) {
        comb = 1
    } else {
        const start = arr[pos]
        for (let i = pos + 1; i < arr.length; i++) {
            const num = arr[i];
            if (num - start <= 3) {
                comb += combinations(i, arr, cache)
            }
        }
    }
    cache[pos] = comb
    return comb
}
