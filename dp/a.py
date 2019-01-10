n = int(input())
h = list(map(int, input().split()))

cost = [float('inf') for _ in range(n)]
cost[0] = 0

for x in range(n):
    if x + 1 < n:
        cost[x + 1] = min(cost[x + 1], cost[x] + abs(h[x + 1] - h[x]))
    
    if x + 2 < n:
        cost[x + 2] = min(cost[x + 2], cost[x] + abs(h[x + 2] - h[x]))

print(cost[n - 1])