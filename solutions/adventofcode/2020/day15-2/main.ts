import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/15/input",
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
    let startingNumbers = str.trim().split(',')
        .map(m => parseInt(m))
    let totalTurn = 30000000

    let turnNo = new Map<number, number>()
    let lastNumber = 0
    for (let turn = 0; turn < totalTurn; turn++) {
        let number = 0
        if (turn < startingNumbers.length) {
            number = startingNumbers[turn]
        } else {
            if (turnNo.has(lastNumber)) {
                let lastTurn = turnNo.get(lastNumber) ?? 0
                number = turn - lastTurn
            } else {
                number = 0
            }
        }
        turnNo.set(lastNumber, turn)
        lastNumber = number
    }
    return lastNumber
}
