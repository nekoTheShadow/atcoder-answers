import collections, heapq

N, M, T = map(int, input().split())
A = list(map(int, input().split()))

G1 = collections.defaultdict(dict)
G2 = collections.defaultdict(dict)
for _ in range(M):
    a, b, c = map(int, input().split())
    a -= 1
    b -= 1
    G1[a][b] = c
    G2[b][a] = c


def dijkstra(g):
    d = [float('inf')] * N
    d[0] = 0
    q = [(0, 0)]
    while q:
        _, u = heapq.heappop(q)
        for v in g[u]:
            if d[u] + g[u][v] < d[v]:
                d[v] = d[u] + g[u][v]
                heapq.heappush(q, (d[v], v))
    return d


t1 = dijkstra(G1)
t2 = dijkstra(G2)
answer = max(A[i] * (T - t1[i] - t2[i]) for i in range(N))
print(answer)
