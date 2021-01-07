function twoSum(nums: number[], target: number): number[] {
    let alreadySeen = new Map<number, number>()
    for (const [index, num] of nums.entries()) {
        let whatWeWant = target - num
        if (alreadySeen.has(whatWeWant)) {
            let otherIndex = alreadySeen.get(whatWeWant)
            return [otherIndex, index]
        } else {
            alreadySeen.set(num, index)
        }
    }
    // we ll never reach here
    // since the question says one answer exists
    return [0, 0]
}
