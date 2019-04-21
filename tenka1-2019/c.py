n = int(input())
s = input()

w = [1 if s[i] == '.' else 0 for i in range(n)]
b = [1 if s[i] == '#' else 0 for i in range(n)]
for i in range(n - 1):
    w[i + 1] += w[i]
    b[i + 1] += b[i]

ans = float('inf')
for i in range(n):
    ans = min(ans, b[i] + w[n - 1] - w[i])
ans = min(ans, b[n - 1], w[n - 1])
print(ans) 