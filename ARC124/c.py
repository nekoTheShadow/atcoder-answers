import math


n = int(input())
a = [None] * n
b = [None] * n
for i in range(n):
    a[i], b[i] = map(int, input().split())

s = set()
s.add((a[0], b[0]))
for i in range(1, n):
    t = set()
    for x, y in s:
        t.add((math.gcd(a[i], x), math.gcd(b[i], y)))
        t.add((math.gcd(b[i], x), math.gcd(a[i], y)))
    s = t

ans = max((x * y) // math.gcd(x, y) for x, y in s)
print(ans)