N, W = map(int, input().split())
w, v = [], []
for i in range(N):
    wi, vi = map(int, input().split())
    w.append(wi)
    v.append(vi)

v_sum = sum(v) + 1
inf = float('inf')
dp = [inf] * v_sum
dp[0] = 0

for i in range(N):
    for j in reversed(range(v_sum)):
        if j + v[i] < v_sum and dp[j] != inf:
            dp[j + v[i]] = min(dp[j + v[i]], dp[j] + w[i])

for i in reversed(range(v_sum)):
    if dp[i] <= W:
        print(i)
        break