import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/13/input",
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
    let lines = str.trim().split('\n')

    const busTimes = lines[1].split(',')
        .map(m => m === 'x' ? '-1' : m)
        .map(m => parseInt(m))

    let minTime = 0
    let jumper = 1
    for (const [time, bus] of busTimes.entries()) {
        if (bus === -1) {
            continue
        }
        for (let newTime = minTime; ; newTime += jumper) {
            if ((newTime + time) % bus === 0) {
                minTime = newTime
                jumper *= bus
                break
            }
        }
    }
    return minTime
}
