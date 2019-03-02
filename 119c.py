import itertools

n, a, b, c = map(int, input().split())
l = tuple(sorted(int(input()) for _ in range(n)))

# kind=0: 使わない
# kind=1: aの材料とする
# kind=2: bの材料とする
# kind=3: cの材料とする
ans = float('inf')
for bit in range(4 ** n):
    kinds = []
    while bit > 0:
        kinds.append(bit % 4)
        bit = bit // 4
    while len(kinds) != n:
        kinds.append(0)
    
    sizes = [0, 0, 0, 0]
    counts = [0, 0, 0, 0]
    for i in range(n):
        sizes[kinds[i]] += l[i]
        counts[kinds[i]] += 1

    _, p, q, r = sizes
    _, s, t, u = counts

    if s == 0 or t == 0 or u == 0: continue
    cost = abs(p - a) + abs(q - b) + abs(r - c) + 10 * max(0, s - 1) + 10 * max(0, t - 1) + 10 * max(0, u - 1)
    ans = min(ans, cost)

print(ans)
 