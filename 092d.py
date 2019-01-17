import itertools

a, b = map(int, input().split())
k = 50
grid = [['.' if x < k else '#' for _ in range(k * 2)] for x in range(k * 2)]

for x, y in itertools.product(range(0, k, 2), range(0, k * 2, 2)):
    if b == 1: break
    grid[x][y] = '#'
    b -= 1

for x, y in itertools.product(range(k + 1, k * 2, 2), range(0, k * 2, 2)):
    if a == 1: break
    grid[x][y] = '.'
    a -= 1

print(k * 2, k * 2)
for row in grid:
    print(''.join(row))