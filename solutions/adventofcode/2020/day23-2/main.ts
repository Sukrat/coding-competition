import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/23/input",
    {
        headers: {
            "cookie": `session=${process.env.ADVENTOFCODE};`
        }
    })
    .then(res => {
        return res.text()
    }).then(data => {
        const answer = solve(data, 10000000)
        console.log(answer)
    })

function solve(str: string, turns: number): number {
    let start = str.trim().split('')
        .map(m => parseInt(m))
    const max = start.reduce((max, c) => max > c ? max : c, -1)
    const rest = (new Array<number>(1000000 - max))
        .fill(0)
        .map((v, i) => max + i + 1)

    const cups = new Map<number, number>()
    const nums = start.concat(rest)
    for (let i = 0; i < nums.length; i++) {
        cups.set(nums[i], nums[(i + 1) % nums.length])
    }

    let curr = nums[0]
    for (let i = 0; i < turns; i++) {
        let pickups = pickUp(curr, cups)
        cups.set(curr, cups.get(pickups[2]) ?? -1)
        pickups.forEach(m => cups.set(m, -1))

        let dest = findDestination(curr, cups)
        let destNext = cups.get(dest) ?? -1

        cups.set(dest, pickups[0])
        cups.set(pickups[0], pickups[1])
        cups.set(pickups[1], pickups[2])
        cups.set(pickups[2], destNext)

        curr = cups.get(curr) ?? -1
    }
    const a = cups.get(1) ?? -1
    const b = cups.get(a) ?? -1

    return a * b
}

function pickUp(select: number, map: Map<number, number>): number[] {
    let curr = select
    let cups = []
    for (let i = 0; i < 3; i++) {
        const next = map.get(curr) ?? -1
        cups.push(next)
        curr = next
    }
    return cups
}

function findDestination(select: number, map: Map<number, number>): number {
    let curr = select - 1
    while (curr > -1) {
        const next = map.get(curr) ?? -1
        if (next !== -1) {
            return curr
        }
        curr--
    }
    curr = 1000000
    while (curr > -1) {
        const next = map.get(curr) ?? -1
        if (next !== -1) {
            return curr
        }
        curr--
    }
    throw new Error()
}
