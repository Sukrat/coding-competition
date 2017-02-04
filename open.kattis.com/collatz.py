def next_num(n):
    if n % 2 == 0:
        return int(n / 2)
    else:
        return int(3 * n + 1)

while True:
    xNo, yNo = list(map(lambda x: int(x), input().split(" ")))
    if xNo == 0 and yNo == 0:
        break
    xDict, yDict = dict([[xNo, 0]]), dict([[yNo, 0]])

    steps = 0
    x = xNo
    if x == 1:
        xDict[x] = 0
    else:
        while True:
            x = next_num(x)
            steps += 1
            xDict[x] = steps
            if x == 1:
                break

    steps = 0
    y = yNo
    if y in xDict:
            print("{0} needs {1} steps, {2} needs {3} steps, they meet at {4}".format(
                xNo, xDict[y], yNo, 0, y))
    else:
        while True:
            y = next_num(y)
            steps += 1
            yDict[y] = steps
            if y in xDict:
                print("{0} needs {1} steps, {2} needs {3} steps, they meet at {4}".format(
                    xNo, xDict[y], yNo, yDict[y], y))
                break
