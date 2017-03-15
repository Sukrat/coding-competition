no = int(input())
sum = 0
for i in range(0, no):
    number = int(input())
    pow = number % 10
    number = int(number / 10)
    sum += number**pow

print(sum)