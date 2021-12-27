import collections

n, k = map(int, input().split())
a = list(map(int, input().split()))

b = [0] * (n+1)
for i in range(n):
    b[i+1] = b[i] + a[i]

h = collections.Counter(b)
ans = 0
for l in range(n):
    h[b[l]] -= 1
    ans += h[b[l]+k]

print(ans)