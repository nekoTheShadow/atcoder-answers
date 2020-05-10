n, m, x = map(int, input().split())
c = []
a = []
for i in range(n):
    row = list(map(int, input().split()))
    c.append(row[0])
    a.append(row[1:])

import itertools

ans = float('inf')
for r in range(n+1):
    for idxs in itertools.combinations(range(n), r=r):
        price = [0]*m
        for idx in idxs:
            for i in range(m):
                price[i] += a[idx][i]
        
        if all(price[i] >= x for i in range(m)):
            ans = min(ans, sum(c[idx] for idx in idxs))

if ans == float('inf'):
    print(-1)
else:
    print(ans)