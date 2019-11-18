import itertools

h, w = map(int, input().split())
s = [input() for _ in range(h)]

diffs = [(0, 1), (0, -1), (1, 0), (-1, 0)]
t = [[0] * w for _ in range(h)]
group = 1
for i, j in itertools.product(range(h), range(w)):
    if t[i][j] != 0:
        continue

    stack = [(i, j)]
    while stack:
        x, y = stack.pop()
        t[x][y] = group
        for dx, dy in diffs:
            nx = x + dx
            ny = y + dy
            if 0 <= nx < h and 0 <= ny < w and t[nx][ny] == 0 and s[x][y] != s[nx][ny]:
                stack.append((nx, ny))
    group += 1

d = [[0] * 2 for _ in range(group)]
for i, j in itertools.product(range(h), range(w)):
    d[t[i][j]][0 if s[i][j] == '.' else 1] += 1

ans = sum(d[i][0] * d[i][1] for i in range(len(d)))
print(ans)