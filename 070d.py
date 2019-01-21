import heapq, collections

n = int(input())
distance = collections.defaultdict(dict)
for _ in range(n - 1):
    a, b, c = map(int, input().split())
    distance[a - 1][b - 1] = c
    distance[b - 1][a - 1] = c
q, k = map(int, input().split())
k -= 1
queries = []
for _ in range(q):
    x, y = map(int, input().split())
    queries.append((x - 1, y - 1))

d = [float('inf')] * (n + 1)
d[k] = 0
queue = [(0, k)]
while queue:
    cost, current = heapq.heappop(queue)
    for nxt in distance[current]:
        if cost + distance[current][nxt] < d[nxt]:
            d[nxt] = cost + distance[current][nxt]
            heapq.heappush(queue, (d[nxt], nxt))

for x, y in queries:
    print(d[x] + d[y])