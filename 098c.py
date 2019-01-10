n = int(input())
s = input()

w = 0
e = s[1:].count('E')

ans = w + e
for i in range(1, n):
    if s[i - 1] == 'W': 
        w += 1
    if s[i] == 'E':
        e -= 1
    ans = min(ans, w + e)

print(ans)
