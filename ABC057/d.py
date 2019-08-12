import collections
import functools
import sys


sys.setrecursionlimit(10 ** 9 + 7)

@functools.lru_cache(maxsize=None)
def c(x, y):
    if y == 0 or x == y:
        return 1
    else:
        return c(x - 1, y - 1) + c(x - 1, y)


n, a, b = map(int, input().split())
v = list(map(int, input().split()))

v.sort(reverse=True)
avg = sum(v[:a]) / a

counter = collections.Counter(v)
if v[0] == v[a - 1]:
    x = counter[v[0]]
    comb = sum(c(x, y) for y in range(a, min(x, b) + 1))
else:
    comb = 1
    for z, y in collections.Counter(v[:a]).items():
        x = counter[z]
        comb *= c(x, y)

print(avg)
print(comb)
