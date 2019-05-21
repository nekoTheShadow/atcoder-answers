s = input()
for x in range(len(s) - 1, 0, -1):
    t = s[:x]
    pivot = len(t) // 2
    if len(t) % 2 == 0 and t[:pivot] == t[pivot:]:
        print(len(t))
        exit()