import collections

n, q = map(int, input().split())
nxts = collections.defaultdict(list)
score = [0] * n 
for _ in range(n - 1):
    a, b = map(int, input().split())
    nxts[a - 1].append(b - 1)
    nxts[b - 1].append(a - 1)
for i in range(q):
    p, x = map(int, input().split())
    score[p - 1] += x


stack = [(0, -1)]
while stack:
    node, parent = stack.pop()
    for nxt in nxts[node]:
        if nxt != parent:
            score[nxt] += score[node]
            stack.append((nxt, node))

ans = ' '.join(map(str, score))
print(ans)
