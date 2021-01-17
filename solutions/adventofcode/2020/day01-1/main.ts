import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/1/input",
    {
        headers: {
            "cookie": `session=${process.env.ADVENTOFCODE};`
        }
    })
    .then(res => {
        return res.text()
    }).then(data => {
        const strs = data.split("\n")
        const expenses = strs.map(str => parseInt(str))
        const answer = solve(expenses)
        console.log(answer)
    })

function solve(expenses: number[]): number {
    const target = 2020
    const alreadySeen = new Set<Number>()

    for (const expense of expenses) {
        const need = target - expense
        if (alreadySeen.has(need)) {
            return need * expense;
        }
        else {
            alreadySeen.add(expense)
        }
    }
    return 0;
}
