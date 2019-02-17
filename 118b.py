n, m = map(int, input().split())
d = [0] * m
for _ in range(n):
    k, *a = map(int, input().split())
    for x in a: d[x - 1] += 1

print(sum(1 for x in d if x == n))