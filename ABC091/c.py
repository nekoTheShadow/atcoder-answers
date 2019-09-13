n = int(input())
points = []
for _ in range(n):
    a, b = map(int, input().split())
    points.append((a, b, 'red'))
for _ in range(n):
    c, d = map(int, input().split())
    points.append((c, d, 'blue'))
points.sort()

used = set()
for i in range(len(points)):
    x1, y1, color1 = points[i]
    if color1 == 'red':
        continue
    
    y = -1
    k = -1
    for j in range(0, i):
        x2, y2, color2 = points[j]
        if color2 == 'red' and not j in used and y2 < y1 and abs(y2 - y1) < abs(y - y1):
            y = y2
            k = j
    
    if k != -1:
        used.add(k)

print(len(used))