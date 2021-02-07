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

    const arr = new Array<boolean>((2 ** 7) * (2 ** 3))
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
        const num = parseInt(`${rowStr}${colStr}`, 2)
        arr[num] = true
    }
    for (let i = 0; i < arr.length; i++) {
        const prev = i - 1 >= 0 ? arr[i - 1] : false
        const curr = arr[i]
        const next = i + 1 < arr.length ? arr[i + 1] : false
        if (prev && !curr && next) {
            return i
        }
    }
    return -1
}
