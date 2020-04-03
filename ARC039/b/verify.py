n, k = map(int, input().split())

dp = [[0] * (k+1) for _ in range(n+1)]
pr = [[0] * (k+1) for _ in range(n+1)]
dp[0][0] = 1

for i in range(n):
    for j in range(k+1):
        for x in range(0, k-j+1):
            if dp[i+1][j+x] < dp[i][j]*x:
                dp[i+1][j+x] = dp[i][j] * x
                pr[i+1][j+x] = j

cur = k
fcts = []
for i in range(n, 0, -1):
    x = dp[i][cur]
    y = dp[i-1][pr[i][cur]]
    fcts.append(x // y)
    cur = pr[i][cur]

print(dp[n][k])
print(fcts)