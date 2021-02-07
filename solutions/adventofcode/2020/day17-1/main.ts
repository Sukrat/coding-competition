import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/17/input",
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

type Point = [number, number, number]
type Storage = boolean[][][]

function solve(str: string): number {
    let grid = str.trim().split('\n')
        .map(m => m.trim().split(''))

    let storage: Storage = []
    let activePoints: Point[] = []

    grid.forEach((row, xI) => {
        row.forEach((cell, yI) => {
            if (cell === '#') {
                let point: Point = [xI, yI, 0]
                setState(point, cell === '#', storage)
                activePoints.push(point)
            }
        })
    })

    for (let cycle = 0; cycle < 6; cycle++) {
        let newStorage: Storage = []
        let alreadySeen: Storage = []
        let newActivePoint: Point[] = []
        let pointsToSearch = Array.from(activePoints)

        while (pointsToSearch.length > 0) {
            let point = pointsToSearch.pop() ?? [0, 0, 0]
            let state = getState(point, storage)
            let neighbours = getNeighbours(point)

            if (getState(point, alreadySeen)) {
                continue
            } else {
                setState(point, true, alreadySeen)
            }

            if (state) {
                pointsToSearch = pointsToSearch.concat(neighbours)
            }

            let activeNeighboursCount = neighbours
                .map(n => getState(n, storage))
                .filter(state => state)
                .length

            if (state && (activeNeighboursCount === 2 || activeNeighboursCount === 3)) {
                setState(point, true, newStorage)
                newActivePoint.push(point)
            } else if (!state && activeNeighboursCount === 3) {
                setState(point, true, newStorage)
                newActivePoint.push(point)
            }
        }
        storage = newStorage
        activePoints = newActivePoint
    }
    return activePoints.length
}

function setState(coord: Point, state: boolean, storage: Storage) {
    const [x, y, z] = coord
    storage[x] = storage[x] ?? []
    storage[x][y] = storage[x][y] ?? []
    storage[x][y][z] = state
}

function getState(coord: Point, storage: Storage): boolean {
    const [x, y, z] = coord
    if (storage[x] && storage[x][y] && storage[x][y][z]) {
        return true
    }
    return false
}

function getNeighbours(coord: Point) {
    const [x, y, z] = coord
    const neighbours = [-1, 0, 1].flatMap(dX => {
        return [-1, 0, 1].flatMap(dY => {
            return [-1, 0, 1].map(dZ => {
                return [x + dX, y + dY, z + dZ] as Point
            })
        })
    }).filter(([x2, y2, z2]) => !(x2 === x && y2 === y && z2 === z))
    return neighbours
}
