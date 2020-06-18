n = int(input())
a = list(map(int, input().split()))
a.sort()
s = 1
for v in a:
    s *= v
    if s > 10**18:
        print(-1)
        exit()
print(s)
