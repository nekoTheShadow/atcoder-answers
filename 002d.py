import itertools

n, m = map(int, input().split())
friends = [[False] * n for _ in range(n)]
for i in range(n):
    friends[i][i] = True
for _ in range(m):
    x, y = map(int, input().split())
    friends[x - 1][y - 1] = True
    friends[y - 1][x - 1] = True

ans = -1
for r in range(1, n + 1):
    for members in itertools.combinations(range(n), r):
        if all(friends[m1][m2] for m1, m2 in itertools.product(members, repeat=2)):
            ans = max(ans, len(members))

print(ans)