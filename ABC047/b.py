import itertools

w, h, n = map(int, input().split())
x = [None] * n
y = [None] * n
a = [None] * n
for i in range(n):
    x[i], y[i], a[i] = map(int, input().split())


area = [[False] * h for _ in range(w)]
for i in range(n):
    for p, q in itertools.product(range(w), range(h)):
        if a[i] == 1 and p < x[i]:  area[p][q] = True
        if a[i] == 2 and p >= x[i]: area[p][q] = True
        if a[i] == 3 and q < y[i]:  area[p][q] = True
        if a[i] == 4 and q >= y[i]: area[p][q] = True

print(sum(sum(1 for v in row if not v) for row in area))