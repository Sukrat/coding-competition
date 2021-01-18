import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/8/input",
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
    const code = str.trim().split('\n')
        .map(m => extractInstruction(m))
    let [isComplete, ans] = findPath(0, 0, code, new Set<number>(), 1)
    return ans
}

function extractInstruction(line: string): [string, number] {
    const match = /^(nop|acc|jmp) ([+-][0-9]+)$/.exec(line)
    if (match === null) {
        throw new Error()
    }
    const op = match[1]
    const arg = parseInt(match[2])
    return [op, arg]
}

function findPath(
    position: number,
    acc: number,
    code: [string, number][],
    visited: Set<number>,
    changeLeft: number
): [boolean, number] {
    let pos = position
    let isComplete = false

    while (true) {
        if (pos >= code.length) {
            isComplete = true
            break
        } else if (visited.has(pos)) {
            isComplete = false
            break
        }

        const [instruction, arg] = code[pos]
        visited.add(pos)

        if (instruction === "acc") {
            acc += arg
            pos += 1
        } else if (instruction === "jmp") {
            pos += arg
        } else if (instruction === "nop") {
            pos += 1
        } else {
            throw new Error()
        }

        if (changeLeft > 0) {
            const newVisited = new Set<number>(visited.keys())
            const newChangeLeft = changeLeft - 1
            let newPos: number = -1
            if (instruction === "jmp") {
                // making it nop
                newPos = (pos - arg) + 1
            } else if (instruction === "nop"
                && arg !== 0
                && (pos - 1) + arg >= 0
                && (pos - 1) + arg < code.length) {
                // making it jmp
                newPos = pos - 1 + arg
            } else {
                continue
            }
            const [newIsComplete, newAcc] = findPath(
                newPos,
                acc,
                code,
                newVisited,
                newChangeLeft
            )
            if (newIsComplete) {
                return [newIsComplete, newAcc]
            }
        }
    }
    return [isComplete, acc]
}
