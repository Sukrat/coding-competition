import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/10/input",
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

function solve(str: string): number | undefined {
    const adapters = str.trim().split('\n')
        .map(m => parseInt(m))

    const sortedAdapters = adapters.sort((a, b) => a - b)

    let oneDiff = 0
    let threeDiff = 1
    for (const [index, adapter] of sortedAdapters.entries()) {
        const prev = sortedAdapters[index - 1] ?? 0
        const diff = adapter - prev
        if (diff === 1) {
            oneDiff++
        } else if (diff === 3) {
            threeDiff++
        }
    }
    return oneDiff * threeDiff
}
