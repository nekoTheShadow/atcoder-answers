n, k = map(int, input().split())
h = list(map(int, input().split()))

costs = [float('inf') for _ in range(n)]
costs[0] = 0

for x in range(n):
    for y in range(x + 1, min(x + k + 1, n)):
        costs[y] = min(costs[y], costs[x] + abs(h[x] - h[y]))

print(costs[n - 1])