x, y = map(int, input().split())
c = 0
while x <= y:
    x *= 2
    c += 1
print(c)