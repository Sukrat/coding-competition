while True:
    no = int(input())
    if(no == 0):
        break
    elif no == 1:
        print("{ }")
        continue
    binary = "{0:b}".format(no - 1)
    power3 = 1
    arr = []
    for i in range(0, len(binary)):
        c = binary[-(i + 1)]
        if (c == "1"):
            arr.append(power3)
        power3 *= 3
    print("{", ", ".join(str(x) for x in arr), "}")
