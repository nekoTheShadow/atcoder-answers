import collections

n = int(input())
nxts = collections.defaultdict(list)
for i in range(n - 1):
    a, b = map(int, input().split())
    nxts[a - 1].append(b - 1)
    nxts[b - 1].append(a - 1)

def f(first):
    d = [-1] * n
    d[first] = 0
    stack = [first]
    while stack:
        current = stack.pop()
        for nxt in nxts[current]:
            if d[nxt] == -1:
                d[nxt] = d[current] + 1
                stack.append(nxt)
    return d

d1 = f(0)
d2 = f(n - 1)
c1 = sum(1 for x1, x2 in zip(d1, d2) if x1 <= x2)
c2 = n - c1


if c1 > c2:
    print('Fennec')
else:
    print('Snuke')
