import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/22/input",
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
    const sections = str.trim().split('\n\n')

    const p1 = sections[0].trim().split('\n')
        .slice(1)
        .map(m => parseInt(m))

    const p2 = sections[1].trim().split('\n')
        .slice(1)
        .map(m => parseInt(m))

    const [player, score] = play(p1, p2, 1)

    return score
}

function play(p1: number[], p2: number[], depth: number): [number, number] {
    const [p1Order, p2Order] = [new Set<string>(), new Set<string>()]
    while (p1.length > 0 && p2.length > 0) {
        const pos1 = p1.join(',')
        const pos2 = p2.join(',')
        if (p1Order.has(pos1) && p2Order.has(pos2)) {
            return [1, p1.reverse().reduce((prev, curr, i) => prev + (curr * (i + 1)), 0)]
        } else {
            p1Order.add(pos1)
            p2Order.add(pos2)
        }

        const c1 = p1[0]
        const c2 = p2[0]

        let p1Win = c1 > c2
        if (p1.length > c1 && p2.length > c2) {
            const [player, score] = play(p1.slice(1, c1 + 1), p2.slice(1, c2 + 1), depth + 1)
            p1Win = player === 1
        }

        if (p1Win) {
            p1 = [...p1.slice(1), c1, c2]
            p2 = [...p2.slice(1)]
        } else {
            p1 = [...p1.slice(1)]
            p2 = [...p2.slice(1), c2, c1]
        }
    }
    const score = (p1.length > 0 ? p1 : p2)
        .reverse()
        .reduce((prev, curr, i) => prev + (curr * (i + 1)), 0)
    return [p1.length > 0 ? 1 : 2, score]
}
