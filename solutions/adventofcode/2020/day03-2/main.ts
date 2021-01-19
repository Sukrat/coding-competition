import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/3/input",
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
    let rows = str.split('\n')
    const map = rows.map(m => m.split(''))

    return solveFor(map, 1, 1)
        * solveFor(map, 3, 1)
        * solveFor(map, 5, 1)
        * solveFor(map, 7, 1)
        * solveFor(map, 1, 2)
}

function solveFor(map: string[][], right: number, down: number): number {
    let pos = 0;
    let count = 0;
    for (let index = 0; index < map.length; index += down) {
        const row = map[index]
        // skipping empty line
        if (row.length === 0) {
            continue
        }
        let whatsThere = row[pos]
        if (whatsThere === "#") {
            count += 1
        }
        pos = (pos + right) % row.length
    }
    return count
}
