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

    let pos = 0;
    let count = 0;
    for (const [index, row] of map.entries()) {
        // skipping empty line
        if (row.length === 0) {
            continue
        }
        let whatsThere = row[pos]
        if (whatsThere === "#") {
            count += 1
        }
        pos = (pos + 3) % row.length
    }
    return count
}
