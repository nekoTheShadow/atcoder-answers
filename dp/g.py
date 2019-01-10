import collections, sys

n, m = map(int, input().split())
pres = collections.defaultdict(list)
nxts = collections.defaultdict(list)
for line in sys.stdin:
    x, y = map(int, line.split())
    pres[y].append(x)
    nxts[x].append(y)

stack, count, nodes = [], [0], []
for node in range(1, n + 1):
    count.append(len(pres[node]))
    if count[node] == 0:
        stack.append(node)

while stack:
    node = stack.pop()
    nodes.append(node)
    for nxt in nxts[node]:
        count[nxt] -= 1
        if count[nxt] == 0:
            stack.append(nxt)

dp = [0] * (n + 1)
for node in nodes:
    for pre in pres[node]:
        dp[node] = max(dp[node], dp[pre] + 1)

print(max(dp))
