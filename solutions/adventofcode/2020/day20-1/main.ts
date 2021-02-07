import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/20/input",
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

type Corner = [string, string, string, string]

function solve(str: string): number {
    const sections = str.trim().split('\n\n')

    const grids = new Map<number, Corner>()
    sections.forEach(section => {
        const lines = section.trim().split('\n')
        const match = /^Tile ([0-9]+):$/.exec(lines[0]) ?? ''
        const id = parseInt(match[1])
        const grid = lines.slice(1)
        grids.set(id, [grid[0], grid.map(m => m[m.length - 1]).join(''), grid[grid.length - 1], grid.map(m => m[0]).join('')])
    })

    const matches = new Map<number, number>()
    for (const [id1, grid1] of grids.entries()) {
        let count = 0
        for (const [id2, grid2] of grids.entries()) {
            if (id1 === id2) {
                continue
            }
            let match = grid1.filter(corner1 => grid2.some(corner2 => {
                return corner1 === corner2 || corner1 === corner2.split('').reverse().join('')
            }))
            if (match.length > 0) {
                count++
            }
        }
        matches.set(id1, count)
    }

    let result = Array.from(matches.entries())
        .filter(([id, count]) => count === 2)
        .reduce((s, [id, count]) => s * id, 1)

    return result
}
