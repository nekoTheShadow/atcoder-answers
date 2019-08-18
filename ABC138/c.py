import heapq

n = int(input())
v = list(map(float, input().split()))

heapq.heapify(v)
while len(v) > 1:
    x = heapq.heappop(v)
    y = heapq.heappop(v)
    heapq.heappush(v, (x + y) / 2)

print(v[0])