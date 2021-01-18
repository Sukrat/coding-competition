import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/4/input",
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

const requiredFields = [
    "byr",
    "iyr",
    "eyr",
    "hgt",
    "hcl",
    "ecl",
    "pid",
    // "cid", // optional
]

function solve(str: string): number {
    let lines = str.split('\n')

    let passportBatch = ''
    let count = 0
    for (let i = 0; i < lines.length; i++) {
        const line = lines[i];

        if (line.length === 0) {
            if (isValid(passportBatch)) {
                count += 1
            }
            passportBatch = ''
        } else {
            passportBatch += ` ${line}`
        }
    }
    return count
}

function isValid(str: string): boolean {
    const passportFields = str.split(' ')
        .filter(m => m.length > 0)
        .map(m => m.split(':', 1)[0])

    const set = new Set<string>(passportFields)
    return requiredFields.every(field => set.has(field))
}
