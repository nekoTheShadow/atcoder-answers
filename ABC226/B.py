import collections

n = int(input())
d = collections.defaultdict(int)
for _ in range(n):
    d[tuple(map(int, input().split()))[1:]] += 1
print(len(d.keys()))