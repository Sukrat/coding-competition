import * as fs from 'fs'
import * as p from 'path'

class Solution {
    public readonly platform: string
    public readonly question: string
    constructor(
        public readonly path: string
    ) {
        const parts = path.trim().split('/')
        const [platform, question] = [parts.slice(1, 2), parts.slice(2, parts.length - 1)]
        this.platform = platform[0]
        this.question = question.join(' ').replace(/-/g, ' ')
    }
}

function getFiles(dir: string): Solution[] {
    const paths = fs.readdirSync(dir)

    return paths
        .flatMap(path => {
            const fullPath = p.join(dir, path)
            if (fs.statSync(fullPath).isDirectory()) {
                return getFiles(fullPath)
            }
            return [new Solution(fullPath)]
        })
}

const files = getFiles('solutions/')
fs.writeFileSync('static/index.json', JSON.stringify(files))
