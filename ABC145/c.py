import itertools, math

n = int(input())
x = [None] * n
y = [None] * n
for i in range(n):
    x[i], y[i] = map(int, input().split())

p = 0
q = 0
for tpl in itertools.permutations(range(n)):
    total = 0
    for i in range(n - 1):
        total += math.hypot(x[tpl[i]] - x[tpl[i + 1]], y[tpl[i]] - y[tpl[i + 1]])
    p += total
    q += 1
print(p / q)
