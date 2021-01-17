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
    const alreadySeen = new Set<number>()

    for (const [index, expense] of expenses.entries()) {
        const needed = target - expense
        const solutionFor2 = solveFor2(needed, alreadySeen)
        if (solutionFor2) {
            return expense * solutionFor2
        } else {
            alreadySeen.add(expense)
        }
    }
    return 0;
}

function solveFor2(target: number, alreadySeen: Set<number>): number | undefined {
    for (const seen of alreadySeen) {
        const need = target - seen
        if (alreadySeen.has(need)) {
            return need * seen;
        }
    }
    return undefined
}
