import itertools

n = int(input())
s = sum(a * b for a, b in itertools.product(range(1, 10), repeat=2))
d = s - n

for a, b in itertools.product(range(1, 10), repeat=2):
    if a * b == d:
        print('{0} x {1}'.format(a, b))
