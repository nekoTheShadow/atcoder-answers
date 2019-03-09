import bisect

a, b, q = map(int, input().split())
slist = list(sorted(int(input()) for _ in range(a)))
tlist = list(sorted(int(input()) for _ in range(b)))
xlist = [int(input()) for _ in range(q)]
dlist = []

for x in xlist:
    i = bisect.bisect_left(slist, x)
    j = bisect.bisect_left(tlist, x)
    d = float('inf')

    # パターン1: 西に走る場合
    if i >= 1 and j >= 1:
        d = min(d, abs(min(slist[i - 1], tlist[j - 1]) - x))

    # パターン2: 東に走る場合
    if i < a and j < b:
        d = min(d, abs(max(slist[i], tlist[j]) - x))

    # パターン3: それぞれ直近の寺院と神社を訪問する場合
    k = min((v for v in (i - 1, i, i + 1) if 0 <= v < a), key=lambda v : abs(slist[v] - x))
    l = min((v for v in (j - 1, j, j + 1) if 0 <= v < b), key=lambda v : abs(tlist[v] - x))
    d = min(d, min(abs(slist[k] - x), abs(tlist[l] - x)) + abs(slist[k] - tlist[l]))

    dlist.append(d)


for d in dlist:
    print(d)
