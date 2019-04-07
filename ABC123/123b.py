import itertools

a = int(input())
b = int(input())
c = int(input())
d = int(input())
e = int(input())

x = float('inf')
for vals in itertools.permutations((a, b, c, d, e)):
    y = 0
    for val in vals[:-1]:
        y += val
        while y % 10 != 0:
            y += 1
    y += vals[-1]
    x = min(x, y)

print(x)
