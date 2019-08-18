import collections

n, k = map(int, input().split())
d = collections.Counter(x % k for x in range(1, n + 1))
ans = 0
for a in range(k):
    b = (k - a) % k
    c = (k - a) % k
    if (b + c) % k == 0:
        ans += d[a] * d[b] * d[c]
print(ans)