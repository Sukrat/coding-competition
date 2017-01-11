funs = dict()
funs[0] = lambda x: "8" * int(x / 7)
funs[1] = lambda x: "10" + funs[0](x - 8)
funs[2] = lambda x: "1" + funs[0](x - 2)
funs[3] = lambda x: "22" + funs[0](x - 10)
funs[4] = lambda x: "20" + funs[0](x - 11)
funs[5] = lambda x: "10" + funs[0](x - 12)
funs[6] = lambda x: "6" + funs[0](x - 6)


no = input()
for i in range(0, int(no)):
    num = int(input())
    max_num = ""
    if num % 2 == 0:
        max_num = "1" * int(num / 2)
    else:
        max_num = "7" + ("1" * int((num - 3) / 2))

    min_num = funs[num % 7](num)
    print("{0} {1}".format(min_num, max_num))
