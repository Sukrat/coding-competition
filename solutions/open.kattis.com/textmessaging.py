no_of_cases = int(input())
for case_no in range(0, no_of_cases):
    line = input().split(" ")
    p, k, n = int(line[0]), int(line[1]), int(line[1])
    freq = list(map(lambda x: int(x), input().split(" ")))
    min_no_of_key_press = 0
    key_press = 1
    i = 1
    freq.sort(reverse=True)
    for f in freq:
        if i <= k:
            i += 1
        else:
            key_press += 1
            i = 2
        min_no_of_key_press += key_press * f
    print("Case #{0:d}:".format(case_no + 1), min_no_of_key_press)
