import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/6/input",
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
    const lines = str.split("\n")
    let sum = 0
    const set = new Set<string>();
    for (const line of lines) {
        if (line.length === 0) {
            sum += set.size
            set.clear()
        }
        line.split('')
            .forEach(m => set.add(m))
    }
    return sum
}

