import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/2/input",
    {
        headers: {
            "cookie": `session=${process.env.ADVENTOFCODE};`
        }
    })
    .then(res => {
        return res.text()
    }).then(data => {
        const strs = data.split("\n")
        const answer = solve(strs)
        console.log(answer)
    })

const regex = /(\d+)-(\d+) (\w+): (\w+)/

function solve(entries: string[]): number {
    return entries.map(entry => {
        let matches = regex.exec(entry)
        if (matches === null) {
            return false
        }
        return isValid(parseInt(matches[1]), parseInt(matches[2]), matches[3], matches[4])
    }).filter(m => m === true).length
}

function isValid(atleast: number, atmost: number, search: string, password: string): boolean {
    const count = password.split('')
        .filter(m => m === search)
        .length

    return atleast <= count && count <= atmost
}
