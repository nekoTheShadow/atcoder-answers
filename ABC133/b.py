import itertools, math

n, d = map(int, input().split())
x = [list(map(int, input().split())) for _ in range(n)]

c = 0
for i, j in itertools.combinations(range(n), 2):
    d = sum((y - z) ** 2 for y, z in zip(x[i], x[j]))
    if d == math.floor(math.sqrt(d)) ** 2:
        c += 1

print(c)
