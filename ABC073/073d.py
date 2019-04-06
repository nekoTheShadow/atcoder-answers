import itertools

N, M, R = map(int, input().split())
r = [int(s) - 1 for s in input().split()]
a, b, c = [0] * M, [0] * M, [0] * M
for i in range(M):
    a[i], b[i], c[i] = map(int, input().split())
    a[i] -= 1
    b[i] -= 1

wf = [[float('inf')] * N for _ in range(N)]
for i in range(M):
    wf[a[i]][b[i]] = c[i]
    wf[b[i]][a[i]] = c[i]

for k, i, j in itertools.product(range(N), repeat=3):
    wf[i][j] = min(wf[i][j], wf[i][k] + wf[k][j])

x = float('inf')
for route in itertools.permutations(r):
    y = sum(wf[route[i]][route[i + 1]] for i in range(R - 1))
    x = min(x, y)

print(x)