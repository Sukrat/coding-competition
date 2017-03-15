max_n = 15
max_t = 30
combinations = [[0 for i in range(0, max_t + 1)] for j in range(0, max_n + 1)]
combinations[1] = [1 for i in range(0, max_t + 1)]
for n in range(2, max_n + 1):
    for t in range(0, max_t + 1):
        temp = sum(combinations[n - 1][0:t + 1])
        combinations[n][t] = temp
# print(combinations)

while True:
    arr = list(map(lambda x: int(x), input().split(" ")))
    if arr[0] == 0:
        break
    NO = arr[0]
    RATINGS = arr[1:]
    sum_of_combinations = 0
    TOTAL_RATING = sum(RATINGS)
    # exact same
    sum_of_combinations += 1
    # less than t
    sum_of_combinations += sum(combinations[NO][0:TOTAL_RATING])
    # equal to t
    t = TOTAL_RATING
    n = NO
    for r in RATINGS:
        N = n - 1
        T = t - r + 1
        sum_of_combinations += sum(combinations[N][T:t + 1])
        t -= r
        n -= 1
    print(sum_of_combinations)
