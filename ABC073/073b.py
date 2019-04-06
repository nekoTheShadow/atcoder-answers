ans = 0

n = int(input())
for _ in range(n):
    l, r = map(int, input().split())
    ans += r - l + 1

print(ans)