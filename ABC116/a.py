ab, bc, ca = map(int, input().split())
x, y, z = sorted((ab, bc, ca))
print(x * y // 2)