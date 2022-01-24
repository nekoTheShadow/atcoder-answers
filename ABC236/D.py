n = int(input())
m = 2 * n
a = [[-1]*m for _ in range(m-1)]
for i in range(m-1):
    a[i][i+1:] = list(map(int, input().split()))

stack = []
for i in range(1, m):
    stack.append((a[0][i], (1<<0) | (1<<i)))

ans = 0
while stack:
    tot, bit = stack.pop()
    d = [i for i in range(m) if (bit & (1<<i)) == 0]

    if len(d) == 0:
        ans = max(ans, tot)
        continue
    
    for i in range(1, len(d)):
        x = d[0]
        y = d[i]
        stack.append((tot ^ a[x][y], bit | (1<<x) | (1<<y)))

print(ans)