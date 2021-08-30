N = int(input())
A = list(map(int, input().split()))
B = list(map(int, input().split()))

AB = [(A[i], B[i]) for i in range(N)]
AB.sort(key=lambda t: t[0])
for i in range(N):
    A[i] = AB[i][0]
    B[i] = AB[i][1]

dp = [[0]*5001 for _ in range(N+1)]
dp[0][0] = 1
ans = 0
for i in range(N):
    for j in range(5001):
        dp[i+1][j] = dp[i][j]
        if B[i] <= j:
            dp[i+1][j] += dp[i][j-B[i]]
            dp[i+1][j] %= 998244353 
        if j <= A[i]-B[i]:
            ans += dp[i][j]
            ans %= 998244353

print(ans)