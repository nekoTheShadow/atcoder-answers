import collections

n = int(input())
s = [input() for _ in range(n)]
m = int(input())
t = [input() for _ in range(m)]

counter1 = collections.Counter(s)
counter2 = collections.Counter(t)
ans = max(counter1[k] - counter2[k] for k in counter1)
print(max(ans, 0))