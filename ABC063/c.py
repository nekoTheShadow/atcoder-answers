n = int(input())
s = [int(input()) for _ in range(n)]

dp = [set() for _ in range(n + 1)]
for i in range(n):
    dp[i + 1].update(dp[i])
    for x in dp[i]:
        dp[i + 1].add(x + s[i])
    dp[i + 1].add(s[i])


ans = 0
for x in dp[i + 1]:
    if x % 10 != 0:
        ans = max(ans, x)
print(ans)
