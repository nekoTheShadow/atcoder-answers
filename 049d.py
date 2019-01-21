def find(parents, x):
    if parents[x] == x:
        return x
    else:
        parents[x] = find(parents, parents[x])
        return parents[x]

def union(parents, x1, x2):
    y1 = find(parents, x1)
    y2 = find(parents, x2)
    if y1 != y2:
        parents[y1] = y2


n, k, l = map(int, input().split())

parents1 = list(range(n))
for _ in range(k):
    p, q = map(int, input().split())
    union(parents1, p - 1, q - 1)

parents2 = list(range(n))
for _ in range(l):
    r, s = map(int, input().split())
    union(parents2, r - 1, s - 1)

import collections

counter = collections.defaultdict(int)
for i in range(n):
    p1, p2 = find(parents1, i), find(parents2, i)
    counter[(p1, p2)] += 1

ans = ' '.join(str(counter[(find(parents1, i), find(parents2, i))]) for i in range(n))
print(ans)