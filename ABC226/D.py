import itertools
import math

n = int(input())
x = [0] * n
y = [0] * n
for i in range(n):
    x[i], y[i] = map(int, input().split())

ans = set()
for i, j in itertools.product(range(n), repeat=2):
    if i==j: continue

    dx = x[i]-x[j]
    dy = y[i]-y[j]
    div = math.gcd(dx, dy)
    ans.add((dx//div, dy//div))

print(len(ans))