import math

n = int(input())

total = 1
factors = set()
for i in range(1, math.ceil(math.sqrt(n))+1):
    if n % i == 0:
        factors.add(i)
        factors.add(n // i)

total = sum(factor for factor in factors if factor != n)
if total == n:
    print('Perfect')
elif total > n:
    print('Abundant')
else:
    print('Deficient')