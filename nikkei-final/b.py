import sys

n, m, k = map(int, input().split())
a = list(map(int, input().split()))
b = list(map(int, input().split()))

if n != m:
    print('X' if n < m else 'Y')
    sys.exit()

# n == m
for p, q in zip(a, b):
    if p == q:
        continue
    print('X' if p < q else 'Y')
    sys.exit()

print('Same')

