n, a = map(int, input().split())
x = list(map(int, input().split()))

# dp[添え字][枚数][合計] = 選び方
total = sum(x)
dp = [[[0] * (total + 1) for _ in range(n + 1)] for _ in range(n + 1)]
dp[0][0][0] = 1

for i in range(n):
    for j in range(n):
        for k in range(total + 1):
            if k + x[i] <= total:
                dp[i + 1][j + 1][k + x[i]] += dp[i][j][k]
            dp[i + 1][j][k] += dp[i][j][k]

ans = 0
for j in range(1, n + 1):
    for k in range(total + 1):
        if k / j == a:
            ans += dp[n][j][k]
print(ans)
