h, w = map(int, input().split())
a = [list(input()) for _ in range(h)]

dp = [[0] * w for _ in range(h)]
dp[0][0] = 1
mod = 10 ** 9 + 7
for i in range(h):
    for j in range(w):
        if i + 1 < h and a[i + 1][j] == '.':
            dp[i + 1][j] = (dp[i + 1][j] + dp[i][j]) % mod
        if j + 1 < w and a[i][j + 1] == '.':
            dp[i][j + 1] = (dp[i][j + 1] + dp[i][j]) % mod

print(dp[h - 1][w - 1])