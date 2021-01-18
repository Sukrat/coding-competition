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
    let intersection: Set<string>[] = []

    for (const line of lines) {
        if (line.length === 0) {
            sum += intersectionOf(intersection).size
            intersection = []
        } else {
            const questions = new Set<string>(line.split(''));
            intersection.push(questions)
        }
    }
    return sum
}

function intersectionOf(arr: Set<string>[]): Set<string> {
    const set = new Set<string>()

    if (arr.length > 0) {
        const main = arr[0]
        for (const elem of main) {
            if (arr.every(s => s.has(elem))) {
                set.add(elem)
            }
        }
    }

    return set
}
