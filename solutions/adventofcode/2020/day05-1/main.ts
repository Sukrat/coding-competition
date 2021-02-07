import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/5/input",
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
    let highestSeatId = 0

    for (const boardingPass of lines) {
        if (boardingPass.length === 0) {
            continue
        }
        const rowStr = boardingPass.substring(0, 7)
            .replace(/F/g, '0')
            .replace(/B/g, '1')
        const colStr = boardingPass.substring(7)
            .replace(/L/g, '0')
            .replace(/R/g, '1')

        const row = parseInt(rowStr, 2)
        const col = parseInt(colStr, 2)

        const seatId = (row * 8) + col
        if (seatId > highestSeatId) {
            highestSeatId = seatId
        }
    }
    return highestSeatId
}
