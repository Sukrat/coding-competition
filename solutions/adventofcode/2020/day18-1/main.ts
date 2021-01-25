import fetch from 'node-fetch'

fetch("https://adventofcode.com/2020/day/18/input",
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
    let expressions = str.trim().split('\n')

    let answer = expressions
        .map(expression => {
            return calculate(expression.split(''))
        })
        .reduce((s, c) => s + c)
    return answer
}

function calculate(terms: string[]): number {
    const stack: string[] = []
    for (let i = 0; i < terms.length; i++) {
        let term = terms[i];
        if (term === '(') {
            stack.push(term)
        } else if (/[0-9]/.test(term)) {
            while (/[0-9]/.test(terms[i + 1])) {
                term += terms[i++]
            }
            stack.push(term)
            reduce(stack)
        } else if (term === ')') {
            let num = stack.pop() ?? ''
            let leftBracket = stack.pop()
            stack.push(num)
            reduce(stack)
        } else if (term === '*' || term === '+') {
            stack.push(term)
        }
    }
    return parseInt(stack.pop() ?? '')
}

function top(stack: string[]) {
    return stack[stack.length - 1]
}

function reduce(stack: string[]) {
    while (stack.length > 1 && /[0-9]+/.test(top(stack))) {
        let num = parseInt(stack.pop() ?? '')
        if (top(stack) === '*') {
            stack.pop()
            let num2 = stack.pop() ?? ''
            stack.push((num * parseInt(num2)).toString())
        } else if (top(stack) === '+') {
            stack.pop()
            let num2 = stack.pop() ?? ''
            stack.push((num + parseInt(num2)).toString())
        } else {
            stack.push(num.toString())
            break
        }
    }
}
