import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/25/input",
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
    const [pk1, pk2] = str.trim().split('\n')
        .map(m => parseInt(m))
    const HASH = 20201227
    const subjectNumber = 7

    const l1 = findLoopSize(pk1, subjectNumber, HASH)
    const l2 = findLoopSize(pk2, subjectNumber, HASH)

    let e1 = pk1
    for (let i = 0; i < l2; i++) {
        e1 = (e1 * pk1) % HASH
    }

    let e2 = pk2
    for (let i = 0; i < l1; i++) {
        e2 = (e2 * pk2) % HASH
    }
    return e1
}

function findLoopSize(publicKey: number, subjectNumber: number, HASH: number) {
    const HARDLOOPSIZE = 1000000000
    let key = subjectNumber
    for (let i = 0; i < HARDLOOPSIZE; i++) {
        if (key === publicKey) {
            return i
        }
        key = (key * subjectNumber) % HASH
    }
    throw new Error()
}
