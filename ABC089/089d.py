h, w, d = map(int, input().split())

coordinates = {}
for i in range(h):
    row = list(map(int, input().split()))
    for j in range(w):
        coordinates[row[j]] = (i, j)

costs = [0] * (h * w + 1)
for i in range(d + 1, h * w + 1):
    x1, y1 = coordinates[i]
    x2, y2 = coordinates[i - d]
    costs[i] = abs(x1 - x2) + abs(y1 - y2) + costs[i - d]

q = int(input())
for _ in range(q):
    l, r = map(int, input().split())
    print(costs[r] - costs[l])
