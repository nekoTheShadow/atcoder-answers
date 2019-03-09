import math

n = int(input())
c, s, f = [], [], []
for _ in range(n - 1):
    x, y, z = map(int, input().split())
    c.append(x)
    s.append(y)
    f.append(z)

for i in range(n - 1):
    t = 0
    for j in range(i, n - 1):
        t = s[j] + f[j] * max(0, math.ceil((t - s[j]) / f[j])) + c[j]
    print(t)

print(0)