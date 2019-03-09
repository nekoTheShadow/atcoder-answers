import itertools

def find(table, x):
    stack = [x]
    while table[stack[-1]] != stack[-1]:
        stack.append(table[stack[-1]])
    
    for i in stack:
        table[i] = stack[-1]
    return table[x]


def union(table, x1, x2):
    p1 = find(table, x1)
    p2 = find(table, x2)
    table[p2] = p1

n = int(input())
points = [tuple(map(int, input().split())) for _ in range(n)]

edges = []
cities = list(range(n))
for j in (0, 1):
    cities.sort(key=lambda city: points[city][j])
    for i in range(n - 1):
        city1, city2 = cities[i], cities[i + 1]
        (x1, y1), (x2, y2) = points[city1], points[city2]
        cost = min(abs(x1 - x2), abs(y1 - y2))
        edges.append((cost, city1, city2))
edges.sort() # key=lambda cost, city1, city2: cost

table = list(range(n))
answer = 0
for cost, city1, city2 in edges:
    parent1, parent2 = find(table, city1), find(table, city2)
    if parent1 != parent2:
        answer += cost
        union(table, city1, city2)

print(answer)