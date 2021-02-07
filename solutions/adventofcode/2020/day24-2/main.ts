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
    const level = 1
    const matrix = new Map<string, [number, number]>([
        ['e', [2, 0]],
        ['w', [-2, 0]],
        ['ne', [1, level]],
        ['nw', [-1, level]],
        ['se', [1, -level]],
        ['sw', [-1, -level]]
    ])

    let blackTiles = new Set<string>()
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
            blackTiles.add(str)
        }
    }

    for (let i = 0; i < 100; i++) {
        blackTiles = flip(blackTiles, matrix)
    }

    return blackTiles.size
}

function flip(blackTiles: Set<string>, matrix: Map<string, [number, number]>): Set<string> {
    let newBlack = new Set<string>()
    let checked = new Set<string>()
    let toCheck = [...blackTiles.values()]
    while (toCheck.length > 0) {
        let curr = toCheck.pop() ?? ''
        checked.add(curr)
        const neighbours = getNeighbour(curr, matrix)
        const blackNeighbours = neighbours
            .filter(n => blackTiles.has(n))
        if (blackTiles.has(curr)) {
            if (!(blackNeighbours.length === 0 || blackNeighbours.length > 2)) {
                newBlack.add(curr)
            }
            toCheck = toCheck.concat(neighbours.filter(n => !checked.has(n)))
        } else {
            if (blackNeighbours.length === 2) {
                newBlack.add(curr)
            }
        }
    }
    return newBlack
}

function getNeighbour(coord: string, matrix: Map<string, [number, number]>): string[] {
    const curr = coord.split(',')
        .map(m => parseInt(m))

    return [...matrix.values()]
        .map(([dx, dy]) => {
            return `${curr[0] + dx},${curr[1] + dy}`
        })
}
