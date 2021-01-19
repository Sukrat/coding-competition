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

    let [dir, x, y] = [0, 0, 0]
    const matrix = new Map<number, [number, number]>([
        [0, [1, 0]],
        [90, [0, 1]],
        [180, [-1, 0]],
        [270, [0, -1]]
    ])
    for (const [ins, arg] of instructions) {
        if (ins === 'N') {
            y += arg
        } else if (ins === 'S') {
            y -= arg
        } else if (ins === 'E') {
            x += arg
        } else if (ins === 'W') {
            x -= arg
        } else if (ins === 'R') {
            dir = (((dir - arg) % 360) + 360) % 360
        } else if (ins === 'L') {
            dir = (((dir + arg) % 360) + 360) % 360
        } else if (ins === 'F') {
            const [i, j] = matrix.get(dir) ?? [0, 0]
            if (i === 0 && j === 0) {
                throw new Error()
            }
            x += i * arg
            y += j * arg
        }
    }
    return Math.abs(x) + Math.abs(y)
}
