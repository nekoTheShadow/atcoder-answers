n, m = map(int, input().split())

imos = [0] * (n + 1)
for _ in range(m):
    l, r = map(int, input().split())
    l -= 1
    r -= 1
    imos[l] += 1
    imos[r + 1] -= 1

for i in range(n):
    imos[i + 1] += imos[i]

ans = 0
for i in range(n):
    if imos[i] == m:
        ans += 1

print(ans)