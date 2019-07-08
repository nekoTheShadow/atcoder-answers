S = input()
MOD = 10 ** 9 + 7

dp = [[0] * 4 for _ in range(len(S) + 1)]
dp[0][0] = 1
for i in range(len(S)):
    for j in range(4):
        if S[i] == '?':
            dp[i + 1][j] = (dp[i + 1][j] + dp[i][j] * 3) % MOD
        else:
            dp[i + 1][j] = (dp[i + 1][j] + dp[i][j]) % MOD
    
    if S[i] == 'A' or S[i] == '?': dp[i + 1][1] = (dp[i + 1][1] + dp[i][0]) % MOD
    if S[i] == 'B' or S[i] == '?': dp[i + 1][2] = (dp[i + 1][2] + dp[i][1]) % MOD
    if S[i] == 'C' or S[i] == '?': dp[i + 1][3] = (dp[i + 1][3] + dp[i][2]) % MOD

print(dp[len(S)][3] % MOD)