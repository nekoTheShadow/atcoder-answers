a, b = map(int, input().split())

x1 = a + (a - 1)
x2 = b + (b - 1)
x3 = a + b

print(max((x1, x2, x3)))