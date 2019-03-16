import math

n = int(input())
a = list(map(int, input().split()))

numer = denom = 0
for i in range(n):
    if a[i] != 0:
        numer += a[i]
        denom += 1

print(math.ceil(numer / denom))