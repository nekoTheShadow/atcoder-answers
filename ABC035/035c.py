n, q = map(int, input().split())
imos = [0] * (n + 1)
for _ in range(q):
    l, r = map(lambda s: int(s) - 1, input().split())
    imos[l] += 1
    imos[r + 1] -= 1

for i in range(n):
    imos[i + 1] += imos[i]

ans = ''.join('0' if imos[i] % 2 == 0 else '1' for i in range(n))
print(ans)