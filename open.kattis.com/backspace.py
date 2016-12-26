st = input()
arr = []
for i in range(0, len(st)):
    if st[i] == "<":
        arr.pop()
    else:
        arr.append(st[i])
print("".join(arr))
