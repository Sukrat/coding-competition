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

type Grid = string[]

function solve(str: string): number {
    const sections = str.trim().split('\n\n')

    const grids = new Map<number, Grid>()
    sections.forEach(section => {
        const lines = section.trim().split('\n')
        const match = /^Tile ([0-9]+):$/.exec(lines[0]) ?? ''
        const id = parseInt(match[1])
        const grid = lines.slice(1)
        grids.set(id, grid)
    })
    const sqLength = Math.sqrt(grids.size)

    const position = new Map<number, [number, number]>()
    const toBeSearched = [Array.from(grids.entries())[0]]
    const searchList = new Set<number>(grids.keys())
    while (toBeSearched.length > 0) {
        const [id, grid] = toBeSearched.pop() ?? [0, []]
        const [x, y] = position.get(id) ?? [0, 0]
        searchList.delete(id)

        for (const id2 of searchList) {
            let grid2 = grids.get(id2) ?? []
            let x2, y2
            for (let t = 0; t < 8; t++) {
                grid2 = rotate(grid2)
                if (top(grid) === bottom(grid2)) {
                    x2 = x - 1
                    y2 = y
                    break
                } else if (right(grid) === left(grid2)) {
                    x2 = x
                    y2 = y + 1
                    break
                } else if (left(grid) === right(grid2)) {
                    x2 = x
                    y2 = y - 1
                    break
                } else if (bottom(grid) === top(grid2)) {
                    x2 = x + 1
                    y2 = y
                    break
                }
                if (t === 3) {
                    grid2 = flip(grid2)
                }
            }
            if (typeof x2 === "number" && typeof y2 === "number") {
                position.set(id2, [x2, y2])
                toBeSearched.push([id2, grid2])
                grids.set(id2, grid2)
            }
        }
        position.set(id, [x, y])
    }

    const mainGridIds: number[][] = new Array(sqLength)
    Array.from(position.entries())
        .sort(([id1, [x1, y1]], [id2, [x2, y2]]) => x1 - x2 === 0 ? y1 - y2 : x1 - x2)
        .reduce((prev, [id, [x, y]]) => {
            const [nX, nY] = [Math.floor(prev / sqLength), prev % sqLength]
            mainGridIds[nX] = mainGridIds[nX] ?? []
            mainGridIds[nX][nY] = mainGridIds[nX][nY] ?? id
            return prev + 1
        }, 0)

    const mainGrid: Grid = mainGridIds.flatMap(row => {
        return row.reduce((prev, curr) => {
            const grid = removeBorders(grids.get(curr) ?? [])
            return prev.length === 0 ? grid : prev.map((p, i) => p.concat(grid[i]))
        }, [] as Grid)
    })

    const pattern = [
        '                  # ',
        '#    ##    ##    ###',
        ' #  #  #  #  #  #   '
    ]
    const matchingPos = pattern.map(row =>
        row.split('')
            .map((m, index) => {
                return m === '#' ? index : -1
            })
            .filter(m => m > -1)
    )

    let grid = mainGrid
    let maxNo = 0
    for (let t = 0; t < 8; t++) {
        grid = rotate(grid)
        const no = findNoOfSeaMonsters(matchingPos, grid)
        maxNo = maxNo < no ? no : maxNo
        if (t === 3) {
            grid = flip(grid)
        }
    }
    const totalHash = grid
        .flatMap(row => row.split(''))
        .filter(m => m === '#')
        .length
    return totalHash - (maxNo * 15)
}

function print(grid: Grid) {
    grid.forEach(m => {
        console.log(m);
    })
    console.log('');
}

function flip(grid: Grid) {
    return grid.map(m => m.split('').reverse().join(''))
}

function rotate(grid: Grid) {
    return grid[0].split('')
        .map((v, i) => {
            return grid.map(row => row[i]).reverse().join('')
        })
}

function top(grid: Grid): string {
    return grid[0]
}

function bottom(grid: Grid): string {
    return grid[grid.length - 1]
}

function right(grid: Grid): string {
    return grid.map(row => row[grid.length - 1]).join('')
}

function left(grid: Grid): string {
    return grid.map(row => row[0]).join('')
}

function removeBorders(grid: Grid): Grid {
    return grid.slice(1, grid.length - 1).map(row => row.slice(1, row.length - 1))
}

function findNoOfSeaMonsters(pattern: number[][], grid: Grid): number {
    let count = 0
    for (let i = 0; i < grid.length - pattern.length; i++) {
        for (let j = 0; j < grid[i].length; j++) {
            let found = pattern.every((row, pi) => {
                return row.every((pj) => {
                    return grid[i + pi][j + pj] === '#'
                })
            })
            if (found) {
                count++
            }
        }
    }
    return count
}
