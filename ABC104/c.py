import itertools

d, g = map(int, input().split())
p, c = [None] * d, [None] * d
for i in range(d):
    p[i], c[i] = map(int, input().split())


ans = float('inf')
for r in range(0, d + 1):
    for choice in itertools.combinations(range(d), r):
        total = sum(100 * (i + 1) * p[i] + c[i] for i in choice)
        count = sum(p[i] for i in choice)

        for i in reversed(range(d)):
            if i in choice:
                continue
            
            finished = True
            for _ in range(p[i]):
                if g <= total:
                    finished = False
                    break
                total += (i + 1) * 100
                count += 1
            
            if finished:
                total += c[i]
            
            if g <= total:
                ans = min(ans, count)

print(ans)