import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/16/input",
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

    const rules: [number, number][] = []
    let i = 0
    for (; i < lines.length; i++) {
        const line = lines[i];
        const match = /^[a-z ]+: ([0-9]+)-([0-9]+) or ([0-9]+)-([0-9]+)/.exec(line)
        if (match === null) {
            break
        }
        rules.push([parseInt(match[1]), parseInt(match[2])],
            [parseInt(match[3]), parseInt(match[4])])
    }
    let sum = 0
    for (i = i + 5; i < lines.length; i++) {
        const line = lines[i];

        const values = line.trim().split(',')
            .map(m => parseInt(m))

        const invalids = values.filter(value => {
            return !rules.some((rule) => {
                return rule[0] <= value && value <= rule[1]
            })
        })
        sum += invalids.reduce((s, invalid) => s + invalid, 0)
    }
    return sum
}
