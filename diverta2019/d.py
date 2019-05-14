n = int(input())

factors = set()
factor = 1
while factor * factor <= n:
    if n % factor == 0:
        factors.add(factor)
        factors.add(n // factor)
    factor += 1

total = 0
for factor in factors:
    k = factor - 1
    if k <= 0:
        continue

    div = n // k
    mod = n % k
    if div == mod:
        total += k

print(total)