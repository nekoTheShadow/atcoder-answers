n = int(input())
d = [int(input()) for _ in range(n)]

d.sort()

ans = -float('inf')
for i in range(n):
    stack = [d[i]]
    for j in range(i + 1, n):
        if stack[-1] < d[j]:
            stack.append(d[j])
    ans = max(ans, len(stack))

print(ans)