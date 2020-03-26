import itertools

n = int(input())
x = [None] * n
y = [None] * n
for i in range(n):
    x[i], y[i] = map(int, input().split())

c = 0
for (x1, y1), (x2, y2), (x3, y3) in itertools.combinations(zip(x, y), 3):
    s = abs((x1-x3)*(y2-y3) - (x2-x3)*(y1-y3))
    if s > 0 and s % 2 == 0:
        c += 1

print(c)