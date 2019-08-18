import itertools

h, w, k = map(int, input().split())

dp = [[0] * w for _ in range(h + 1)]
dp[0][0] = 1

bits = []
for r in range(w + 1):
    for bit in itertools.combinations(range(w - 1), r):
        if all(y - x > 1 for x, y in zip(bit, bit[1:])):
            bits.append(bit)

mod = 10 ** 9 + 7

for i in range(h):
    for j in range(w):
        for bit in bits:
            if j in bit:
                dp[i + 1][j + 1] += dp[i][j]
                dp[i + 1][j + 1] %= mod
            elif (j - 1) in bit:
                dp[i + 1][j - 1] += dp[i][j]
                dp[i + 1][j - 1] %= mod
            else:
                dp[i + 1][j] += dp[i][j]
                dp[i + 1][j] %= mod

print(dp[h][k - 1])

