import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/12/input",
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
    let instructions: [string, number][] = str.trim().split('\n')
        .map(m => {
            const ins = m.slice(0, 1)
            const arg = parseInt(m.slice(1))
            return [ins, arg]
        })

    let [x, y] = [0, 0]
    let wDir = 0
    let [wX, wY] = [10, 1]

    const matrix = new Map<number, [number, number]>([
        [0, [1, 0]],
        [90, [0, 1]],
        [180, [-1, 0]],
        [270, [0, -1]]
    ])
    for (const [ins, arg] of instructions) {
        if (ins === 'N') {
            wY += arg
        } else if (ins === 'S') {
            wY -= arg
        } else if (ins === 'E') {
            wX += arg
        } else if (ins === 'W') {
            wX -= arg
        } else if (ins === 'R') {
            wDir = (((wDir - arg) % 360) + 360) % 360
        } else if (ins === 'L') {
            wDir = (((wDir + arg) % 360) + 360) % 360
        } else if (ins === 'F') {
            x += wX * arg
            y += wY * arg
        }

        let [newX, newY] = [wX, wY]
        if (wDir === 90) {
            newX = -wY
            newY = wX
        } else if (wDir === 180) {
            newX = -wX
            newY = -wY
        } else if (wDir === 270) {
            newX = wY
            newY = -wX
        }
        wX = newX
        wY = newY
        wDir = 0
    }

    return Math.abs(x) + Math.abs(y)
}
