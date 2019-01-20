import collections, itertools

n = int(input())
counter = collections.defaultdict(int)
for _ in range(n):
    s = input()
    counter[s[0]] += 1

ans = 0
for a, b, c in itertools.combinations('MARCH', 3):
    ans += counter[a] * counter[b] * counter[c]

print(ans)