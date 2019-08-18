import itertools

n, m = map(int, input().split())
x = [None] * n
y = [None] * n
z = [None] * n
for i in range(n):
    x[i], y[i], z[i] = map(int, input().split())

ans = -float('inf')
for bit in range(2 ** 3):
    p = 1 if bit & (1 << 0) == 0 else -1
    q = 1 if bit & (1 << 1) == 0 else -1
    r = 1 if bit & (1 << 2) == 0 else -1

    idxs = list(sorted(range(n), key=lambda i: x[i] * p + y[i] * q + z[i] * r, reverse=True))
    s = t = u = 0
    for i in idxs[:m]:
        s += x[i]
        t += y[i]
        u += z[i]
    ans = max(ans, abs(s) + abs(t) + abs(u))

print(ans)
