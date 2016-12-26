def add(i1, i2):
    return (i1 + i2) % mod

str = input()

count_0 = 0
mod = 1000000007
power2 = 1
prev = 0
next = 0
for i in range(0, len(str)):
    s = str[len(str) - 1 - i]
    next = 0
    if s == "0":
        next = add(next, prev)
        count_0 = add(count_0, power2)
    elif s == "1":
        next = add(next, prev)
        next = add(next, count_0)
    else:
        next = add(next, 2 * prev)
        next = add(next, count_0)
        count_0 = add(2 * count_0, (power2)) if i > 0 else 1
        power2 = (power2 * 2) % mod
    prev = next
print(next % mod)
