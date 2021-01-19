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

    const arrived = parseInt(lines[0])
    const busTimes = lines[1].split(',')
        .filter(m => m !== 'x')
        .map(m => parseInt(m))

    var lowestWait = Number.MAX_VALUE
    var busNumber = -1
    for (const busTime of busTimes) {
        var minutesToWait = busTime - (arrived % busTime)
        if (lowestWait > minutesToWait) {
            lowestWait = minutesToWait
            busNumber = busTime
        }
    }
    return busNumber * lowestWait
}
