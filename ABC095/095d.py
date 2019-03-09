n, c = map(int, input().split())

x = [None] * (n + 2)
v = [None] * (n + 2)
for i in range(1, n + 1):
    x[i], v[i] = map(int, input().split())
x[0] = 0
x[n + 1] = c

dp = [[0] * (n + 2) for _ in range(2)]

cal = 0
for i in range(1, n + 1):
    cal -= x[i] - x[i - 1]
    cal += v[i]
    dp[0][i] = max(dp[0][i - 1], cal)

cal = 0
for i in range(n, 0, -1):
    cal -= x[i + 1] - x[i]
    cal += v[i]
    dp[1][i] = max(dp[1][i + 1], cal)

maxcal = 0
for i in range(n + 1):
    maxcal = max(maxcal, dp[0][i] + dp[1][i + 1] - x[i])
    maxcal = max(maxcal, dp[0][i] + dp[1][i + 1] - c + x[i + 1])

print(maxcal)