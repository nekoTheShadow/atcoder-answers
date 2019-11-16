import collections

n, t = map(int, input().split())
tpls = list(sorted(tuple(map(int, input().split())) for _ in range(n)))
a = [None] * n
b = [None] * n
for i in range(n):
    a[i], b[i] = tpls[i]

dp = [[0] * (t + 1) for _ in range(n + 1)]
for i in range(n):
    for j in range(t):
        dp[i + 1][j] = max(dp[i + 1][j], dp[i][j])
        k = min(t, j + a[i])
        dp[i + 1][k] = max(dp[i + 1][k], dp[i][j] + b[i])
print(max(map(max, dp)))