s = int(input())
v = 10 ** 9
x1 = 0
y1 = 0
x2 = v
y2 = 1
x3 = (v - s % v) % v
y3 = (s + x3) // v
print(x1, y1, x2, y2, x3, y3)