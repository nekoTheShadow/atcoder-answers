a = int(input())
b = int(input())
a, b = sorted((a, b))

x = b - a
y = 0
while a != b:
    y += 1
    b += 1
    if b == 10:
        b = 0

print(min(x, y))