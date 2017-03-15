no = input()
temp_list = list(filter(lambda x: int(x) < 0, input().split(" ")))
print(len(temp_list))
