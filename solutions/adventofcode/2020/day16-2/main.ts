import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/16/input",
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
    let sections = str.trim().split('\n\n')

    let rules: [string, number, number, number, number][] = sections[0].trim().split('\n')
        .map(rule => {
            const match = /^([a-z ]+): ([0-9]+)-([0-9]+) or ([0-9]+)-([0-9]+)/.exec(rule)
            if (match === null) {
                throw new Error()
            }
            return [match[1],
            parseInt(match[2]), parseInt(match[3]),
            parseInt(match[4]), parseInt(match[5])]
        })

    const myTicket = sections[1].trim().split('\n')[1]
        .split(',').map(m => parseInt(m))

    const nearbyTickets = sections[2].trim().split('\n').slice(1)
        .map(ticket => {
            return ticket.split(',').map(m => parseInt(m))
        })

    const validTickets = nearbyTickets
        .filter(ticket => {
            return ticket.every(ticketNo => {
                return rules.some(rule => {
                    const [name, min1, max1, min2, max2] = rule
                    return (min1 <= ticketNo && ticketNo <= max1)
                        || (min2 <= ticketNo && ticketNo <= max2)
                })
            })
        })

    const cols = new Set<number>(rules.map((rule, index) => index))
    let result = 1
    while (cols.size > 0) {
        const tempCols = Array.from(cols.values())
        for (const colNo of tempCols) {
            const matches = rules
                .filter(rule => {
                    const [name, min1, max1, min2, max2] = rule
                    return validTickets
                        .every(ticket => {
                            const ticketNo = ticket[colNo]
                            return (min1 <= ticketNo && ticketNo <= max1)
                                || (min2 <= ticketNo && ticketNo <= max2)
                        })
                })
            if (matches.length === 1) {
                const [name, min1, max1, min2, max2] = matches[0]
                cols.delete(colNo)
                rules = rules.filter((rule) => rule[0] !== name)
                if (name.startsWith('departure')) {
                    result *= myTicket[colNo]
                }
            }
        }
    }
    return result
}
