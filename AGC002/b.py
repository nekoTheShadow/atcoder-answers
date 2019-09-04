n, m = map(int, input().split())
x = [None] * m
y = [None] * m
for i in range(m):
    x[i], y[i] = map(lambda s: int(s) - 1, input().split())

red = [False] * n
balls = [1] * n
red[0] = True
for i in range(m):
    if red[x[i]]:
        red[y[i]] = True
    balls[x[i]] -= 1
    balls[y[i]] += 1
    if balls[x[i]] == 0:
        red[x[i]] = False

ans = sum(1 for v in red if v)
print(ans)