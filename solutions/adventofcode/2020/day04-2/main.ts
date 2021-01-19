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
    const map = new Map<string, string>()
    str.split(' ')
        .filter(m => m.length > 0)
        .forEach(m => {
            const field = m.split(':', 2)
            map.set(field[0], field[1])
        })

    const areFieldsPresent = [
        "byr",
        "iyr",
        "eyr",
        "hgt",
        "hcl",
        "ecl",
        "pid",
        // "cid", // optional
    ].every(field => map.has(field))

    return areFieldsPresent
        && checkNumberIsBetween(parseInt(map.get('byr') ?? ""), 1920, 2002)
        && checkNumberIsBetween(parseInt(map.get('iyr') ?? ""), 2010, 2020)
        && checkNumberIsBetween(parseInt(map.get('eyr') ?? ""), 2020, 2030)
        && checkHeight(map.get('hgt') ?? "")
        && checkHairColor(map.get('hcl') ?? "")
        && checkEyeColor(map.get('ecl') ?? "")
        && checkPassportId(map.get('pid') ?? "")
}

function checkNumberIsBetween(test: number, atleast: number, atmost: number): boolean {
    return atleast <= test && test <= atmost
}

function checkHeight(test: string): boolean {
    const re = /^([0-9]+)(cm|in)$/
    const match = re.exec(test)
    if (match !== null) {
        const height = parseInt(match[1])
        const metric = match[2]
        if (metric === "cm") {
            return 150 <= height && height <= 193
        } else {
            return 59 <= height && height <= 76
        }
    }
    return false
}

function checkHairColor(test: string): boolean {
    const re = /^#[0-9a-f]{6}$/
    return re.test(test)
}

function checkEyeColor(test: string): boolean {
    const re = /^(amb|blu|brn|gry|grn|hzl|oth)$/
    return re.test(test)
}

function checkPassportId(test: string): boolean {
    const re = /^[0-9]{9}$/
    return re.test(test)
}
