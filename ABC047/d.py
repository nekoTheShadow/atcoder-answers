import collections

n, t = map(int, input().split())
a = list(map(int, input().split()))

minimum = a[0]
diffs = []
for i in range(1, n):
    diffs.append(a[i] - minimum)
    minimum = min(minimum, a[i])

c = collections.Counter(diffs)
print(c[max(c)])