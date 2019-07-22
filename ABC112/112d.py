import math, bisect

n, m = map(int, input().split())

s = set()
for x in range(1, math.ceil(math.sqrt(m)) + 1):
    if m % x == 0:
        s.add(x)
        s.add(m // x)

t = list(sorted(s))
i = bisect.bisect_left(t, n)
print(m // t[i])