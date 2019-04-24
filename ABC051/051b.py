import itertools

k, s = map(int, input().split())
ans = 0
for x, y in itertools.product(range(k + 1), repeat=2):
    z = s - x - y
    if 0 <= z <= k:
        ans += 1

print(ans)