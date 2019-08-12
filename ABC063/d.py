import math

n, a, b = map(int, input().split())
h = [int(input()) for _ in range(n)]

def is_ok(x):
    c = 0
    for i in range(n):
        t = h[i] - b * x
        if t <= 0:
            continue
        c += math.ceil(t / (a - b))
    return c <= x

ng = 0
ok = 10 ** 9
while abs(ok - ng) > 1:
    mi = (ok + ng) // 2
    if is_ok(mi):
        ok = mi
    else:
        ng = mi

print(ok)
