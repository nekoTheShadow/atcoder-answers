n = int(input())
s = input()

ans = 0
for i in range(n - 1):
    x, y = s[0:i+1], s[i+1:]
    ans = max(ans, len(set(x) & set(y)))

print(ans)