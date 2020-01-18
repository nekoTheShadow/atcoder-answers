n = int(input())
robots = []
for _ in range(n):
    x, l = map(int, input().split())
    robots.append((x + l, x - l))
robots.sort()

cur = -float('inf')
ans = 0
for i in range(n):
    t, s = robots[i]
    if cur <= s:
        ans += 1
        cur = t

print(ans)