import operator
import collections

n, k = map(int, input().split())
sushies = []
for _ in range(n):
    t, d = map(int, input().split())
    sushies.append((t, d))

sushies.sort(key=operator.itemgetter(1), reverse=True)

dishes = sushies[:k]
c = collections.Counter(t for t, d in dishes)
a = sum(d for t, d in dishes)
answer = a + len(c) ** 2
x = k - 1
for t, d in sushies[k:]:
    if t in c:
        continue

    while x >= 0:
        if c[dishes[x][0]] > 1:
            break
        x -= 1

    if x == -1:
        break


    a = a + d - dishes[x][1]
    c[t] += 1
    c[dishes[x][0]] -= 1
    del dishes[x]
    dishes.append((t, d))

    answer = max(answer, a + len(c) ** 2)

print(answer)
