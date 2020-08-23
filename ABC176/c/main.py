n = int(input())
a = list(map(int, input().split()))

ans = 0
m = a[0]
for i in range(n):
    if a[i] < m:
        ans += m - a[i]
    else:
        m = a[i]
print(ans)