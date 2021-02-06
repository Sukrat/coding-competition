import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/24/input",
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
    const instructions = str.trim().split('\n')
    console.log(instructions);
    const level = 1
    const matrix = new Map([
        ['e', [2, 0]],
        ['w', [-2, 0]],
        ['ne', [1, level]],
        ['nw', [-1, level]],
        ['se', [1, -level]],
        ['sw', [-1, -level]]
    ])

    const blackTiles = new Map<string, boolean>()
    for (const inst of instructions) {
        let [x, y] = [0, 0]
        for (let i = 0; i < inst.length;) {
            let move = inst[i]
            if (inst[i] === 'n' || inst[i] === 's') {
                move = inst.slice(i, i + 2)
                i += 2
            } else {
                i++
            }
            const [dx, dy] = matrix.get(move) ?? [0, 0]
            x += dx
            y += dy
        }
        const str = `${x},${y}`
        if (blackTiles.has(str)) {
            blackTiles.delete(str)
        } else {
            blackTiles.set(str, true)
        }
    }
    return blackTiles.size
}
