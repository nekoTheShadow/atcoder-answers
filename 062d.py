import heapq

n = int(input())
a = list(map(int, input().split()))

sum1 = 0
sum2 = 0
left = []
used = set()
unused = []

for i in range(n):
    sum1 += a[i]
    heapq.heappush(left, (a[i], i))


for v, i in sorted((a[i], i) for i in range(n, n * 3)):
    if len(used) < n:
        sum2 += v
        used.add((v, i))
    else:
        heapq.heappush(unused, (v, i))

ans = sum1 - sum2
for i in range(n, 2 * n):
    # v1: これから挿入しようとしている値
    # v2: 現状の最小値
    v1 = a[i]
    v2, j = left[0]
    if v2 < v1:
        sum1 = sum1 + v1 - v2
        heapq.heappop(left)
        heapq.heappush(left, (v1, i))

    # v1: これから削除する可能性がある値
    # v2: 候補のうちで最小の値
    while True:
        v2, j = unused[0]
        if i <= j:
            break
        heapq.heappop(unused)
        
    if (v1, i) in used:
        sum2 = sum2 - v1 + v2
        used.remove((v1, i))
        used.add((v2, j))
        heapq.heappop(unused)

    ans = max(ans, sum1 - sum2)

print(ans)
    
    