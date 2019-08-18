import collections

n, m = map(int, input().split())
a = list(map(int, input().split()))

b = [0] * (n + 1)
for i in range(n):
    b[i + 1] = b[i] + a[i]

d = collections.Counter(x % m for x in b)
ans = sum(v * (v - 1) // 2 for v in d.values())
print(ans)