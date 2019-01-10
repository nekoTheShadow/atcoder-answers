import itertools

n = int(input())
happiness = [list(map(int, input().split())) for _ in range(n)]
dp = [[0, 0, 0] for _ in range(n + 1)] 

tpls = tuple((i, j) for i, j in itertools.product(range(3), repeat=2) if i != j)
for x in range(n):
    for i, j in tpls:
        dp[x + 1][j] = max(dp[x + 1][j], dp[x][i] + happiness[x][i])

print(max(dp[n]))
