n, k = map(int, input().split())
s = input()

c = 0
for i in range(n):
    if s[i] == 'R' and 0 <= i + 1 < n and s[i+1] == 'R': c += 1
    if s[i] == 'L' and 0 <= i - 1 < n and s[i-1] == 'L': c += 1
ans = min(n - 1, c + 2 * k)
print(ans)