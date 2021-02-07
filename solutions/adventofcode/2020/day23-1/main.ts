import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/23/input",
    {
        headers: {
            "cookie": `session=${process.env.ADVENTOFCODE};`
        }
    })
    .then(res => {
        return res.text()
    }).then(data => {
        const answer = solve(data, 100)
        console.log(answer)
    })

function solve(str: string, turns: number): string {
    let cups = str.trim().split('')
        .map(m => parseInt(m))
    const len = cups.length

    for (let turn = 0; turn < turns; turn++) {
        const i = turn % len

        let newCups = [...cups.slice(i), ...cups.slice(0, i)]
        const cup = newCups[0]
        const pickup = newCups.splice(1, 3)

        const dest = findDestination(newCups)
        const index = newCups.indexOf(dest)

        newCups = [...newCups.slice(0, index + 1), ...pickup, ...newCups.slice(index + 1)]
        cups = [...newCups.slice(len - i), ...newCups.slice(0, len - i)]
    }
    const i = cups.indexOf(1)
    return [...cups.slice(i + 1), ...cups.slice(0, i)].join('')
}

function findDestination(cups: number[]) {
    const curr = cups[0]

    const less = cups.slice(1)
        .filter(m => m <= curr - 1)
        .sort((a, b) => b - a)
    if (less.length > 0) {
        return less[0]
    } else {
        const greatest = cups.slice(1)
            .sort((a, b) => b - a)
        return greatest[0]
    }
}
