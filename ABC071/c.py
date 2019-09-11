import collections

n = int(input())
a = list(map(int, input().split()))

d = collections.defaultdict(int)
edges = []
for edge in a:
    d[edge] += 1
    if d[edge] % 2 == 0:
        edges.append(edge)

edges.sort(reverse=True)
if len(edges) < 2:
    print(0)
else:
    print(edges[0] * edges[1])
