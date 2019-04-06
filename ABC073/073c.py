import collections

d = collections.defaultdict(int)

n = int(input())
for _ in range(n):
    a = int(input())
    d[a] += 1

ans = 0
for v in d.values():
    if v % 2 != 0:
        ans += 1

print(ans)