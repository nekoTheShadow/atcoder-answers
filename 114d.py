import collections

n = int(input())

counts = collections.defaultdict(int)
for x in range(2, n + 1):
    for y in range(2, n + 1):
        while x % y == 0:
            counts[y] += 1
            x //= y

def f(a):
    return sum(1 for b in counts if counts[b] >= a - 1)

answer = f(75) \
           + f(5) * (f(5) - 1) * (f(3) - 2) // 2 \
           + f(15) * (f(5) - 1) \
           + f(25) * (f(3) - 1)
print(answer)
