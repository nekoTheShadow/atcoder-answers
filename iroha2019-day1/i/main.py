import collections, heapq

n, m, k = map(int, input().split())
nxts = collections.defaultdict(list)

for _ in range(m):
    a, b, c = map(int, input().split())
    a -= 1
    b -= 1
    nxts[a].append((b, c))
    nxts[b].append((a, c))

score = [float('inf')] * n
score[0] = 0
q = []
heapq.heappush(q, (0, 0, 0))
while q:
    _, cur, last = heapq.heappop(q)
    for nxt, distance in nxts[cur]:
        if last == distance:
            if score[cur] < score[nxt]:
                score[nxt] = score[cur]
                heapq.heappush(q, (score[nxt], nxt, distance))
        else:
            if score[cur] + 1 < score[nxt]:
                score[nxt] = score[cur] + 1
                heapq.heappush(q, (score[nxt], nxt, distance))

if score[n-1] == float('inf'):
    print(-1)
else:
    print(score[n-1]*k)
