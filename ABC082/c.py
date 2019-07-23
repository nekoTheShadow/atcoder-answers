import collections

n = int(input())
a = list(map(int, input().split()))

c = collections.Counter(a)
ans = 0
for k, v in c.items():
    if k < v: ans += v - k
    if k > v: ans += v
print(ans)