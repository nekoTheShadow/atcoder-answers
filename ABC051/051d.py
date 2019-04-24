import itertools

n, m = map(int, input().split())
d = []
for _ in range(m):
    a, b, c = map(int, input().split())
    d.append((a - 1, b - 1, c))

wf = [[float('inf')] * n for _ in range(n)]
for a, b, c in d:
    wf[a][b] = wf[b][a] = c
for i in range(n):
    wf[i][i] = 0

for k, i, j in itertools.product(range(n), repeat=3):
    wf[i][j] = min(wf[i][j], wf[i][k] + wf[k][j])

ans = 0
for a, b, c in d:
    used = False
    for i in range(n):
        for j in range(i + 1, n):
            if wf[i][a] + c + wf[b][j] == wf[i][j] \
                    or wf[i][b] + c + wf[a][j] == wf[i][j] \
                    or wf[j][a] + c + wf[b][i] == wf[j][i] \
                    or wf[j][b] + c + wf[a][i] == wf[j][i]:
                used = True
            
    if not used:
        ans += 1

print(ans)
