import itertools, sys

n = int(input())
a = [list(map(int, input().split())) for _ in range(n)]

exists = [[True] * n for _ in range(n)]
for k, i, j in itertools.product(range(n), repeat=3):
    if a[i][k] + a[k][j] == a[i][j] and i != k and k != j:
        exists[i][j] = False
    elif a[i][k] + a[k][j] < a[i][j]:
        print(-1)
        sys.exit()

ans = 0
for i in range(n):
    for j in range(i + 1, n):
        if exists[i][j]:
            ans += a[i][j]
print(ans)