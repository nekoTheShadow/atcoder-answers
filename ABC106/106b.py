n = int(input())
ans = 0
for x in range(1, n + 1, 2):
    if sum(1 for y in range(1, x + 1) if x % y == 0) == 8:
        ans += 1
print(ans)