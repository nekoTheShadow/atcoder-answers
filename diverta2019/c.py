x = y = z = 0
ans = 0

n = int(input())
for _ in range(n):
    s = input()
    ans += s.count("AB")

    if s[0] == 'B' and s[-1] == 'A':
        x += 1
    elif s[-1] == 'A':
        y += 1
    elif s[0] == 'B':
        z += 1

if y == 0 and z == 0:
    ans += max(x - 1, 0)
else:
    ans += x + min(y, z)

print(ans)