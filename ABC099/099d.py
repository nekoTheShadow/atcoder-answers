import itertools

N, C = map(int, input().split())
D = [list(map(int, input().split())) for _ in range(C)]
c = [[int(s) - 1 for s in input().split()] for _ in range(N)]

m0 = [0] * C
m1 = [0] * C
m2 = [0] * C
for i, j in itertools.product(range(N), repeat=2):
    if (i + j) % 3 == 0: m0[c[i][j]] += 1
    if (i + j) % 3 == 1: m1[c[i][j]] += 1
    if (i + j) % 3 == 2: m2[c[i][j]] += 1

ans = float('inf')
for x, y, z in itertools.permutations(range(C), 3):
    point = 0
    for i in range(C): point += D[i][x] * m0[i]
    for i in range(C): point += D[i][y] * m1[i]
    for i in range(C): point += D[i][z] * m2[i]
    ans = min(ans, point)

print(ans)
