import collections, itertools

h, w = map(int, input().split())
a = [input() for _ in range(h)]

b = [[None] * w for _ in range(h)]
count = 0
for x in range(h):
    for y in range(w):
        if b[x][y] is not None:
            continue
        b[x][y] = count
        b[h-x-1][y] = count
        b[x][w-y-1] = count
        b[h-x-1][w-y-1] = count
        count += 1

d1 = collections.Counter(a[x][y] for x, y in itertools.product(range(h), range(w)))
d2 = collections.Counter(b[x][y] for x, y in itertools.product(range(h), range(w)))

for k2 in sorted(d2, key=lambda k: d2[k], reverse=True):
    ok = False
    v2 = d2[k2]
    for k1 in sorted(d1, key=lambda k: d1[k], reverse=True):
        v1 = d1[k1]
        if v2 <= v1:
            d1[k1] -= v2
            ok = True
            break
    if not ok:
        print('No')
        exit(0)

print('Yes')