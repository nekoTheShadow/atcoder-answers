n = int(input())
h = list(map(int, input().split()))

c = 0
ans = 0
for x in range(n - 1):
    if h[x] >= h[x + 1]:
        c += 1
    else:
        ans = max(ans, c)
        c = 0

ans = max(ans, c)
print(ans)
