import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/11/input",
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
    let seatGrid = str.trim().split('\n')
        .map(m => m.trim().split(''))

    let hasChanged = true
    while (hasChanged) {
        hasChanged = false

        let workingArea = copy(seatGrid)
        let count = fillEmptySeats(seatGrid, workingArea)
        seatGrid = workingArea

        workingArea = copy(seatGrid)
        count += removeSeats(seatGrid, workingArea)
        seatGrid = workingArea

        hasChanged = count > 0
    }
    return countSeats(seatGrid)
}

function copy(seatGrid: string[][]): string[][] {
    return seatGrid
        .map(m => Array.from(m))
}

function fillEmptySeats(seatGrid: string[][], workingArea: string[][]): number {
    let changed = 0
    for (const [i, seatRow] of seatGrid.entries()) {
        for (const [j, seat] of seatRow.entries()) {
            workingArea[i][j] = seat
            if (seat === 'L') {
                const count = countOccupiedAdjacent(seatGrid, i, j)
                if (count === 0) {
                    workingArea[i][j] = '#'
                    changed++
                }
            }
        }
    }
    return changed
}

function removeSeats(seatGrid: string[][], workingArea: string[][]): number {
    let changed = 0
    for (const [i, seatRow] of seatGrid.entries()) {
        for (const [j, seat] of seatRow.entries()) {
            workingArea[i][j] = seat
            if (seat === '#') {
                const count = countOccupiedAdjacent(seatGrid, i, j)
                if (count >= 4) {
                    workingArea[i][j] = 'L'
                    changed++
                }
            }
        }
    }
    return changed
}

function countSeats(seatGrid: string[][]): number {
    let count = 0
    for (const [i, seatRow] of seatGrid.entries()) {
        for (const [j, seat] of seatRow.entries()) {
            if (seat === '#') {
                count++
            }
        }
    }
    return count
}

function countOccupiedAdjacent(seatGrid: string[][], i: number, j: number): number {
    let count = 0
    for (let row = i - 1; row <= i + 1; row++) {
        for (let col = j - 1; col <= j + 1; col++) {
            if (row === i && col === j) {
                continue
            }
            if (row >= 0 && row < seatGrid.length) {
                const seatRow = seatGrid[row]
                if (col >= 0 && col < seatRow.length) {
                    const seat = seatRow[col]
                    if (seat === '#') {
                        count++
                    }
                }
            }
        }
    }
    return count
}
