import heapq

k, t = map(int, input().split())
a = list(map(int, input().split()))

q = []
for i in range(t):
    heapq.heappush(q, (a[i] * -1, i))

cakes = [-1]
for _ in range(k):
    count1, cake1 = heapq.heappop(q)
    if cakes[-1] == cake1 and len(q) > 0:
        count2, cake2 = heapq.heappop(q)
        cakes.append(cake2)

        heapq.heappush(q, (count1, cake1))
        if count2 + 1 < 0:
            heapq.heappush(q, (count2 + 1, cake2))
    else:
        cakes.append(cake1)
        if count1 + 1 < 0:
            heapq.heappush(q, (count1 + 1, cake1))

ans = sum(1 for i in range(len(cakes) - 1) if cakes[i] == cakes[i + 1])
print(ans)