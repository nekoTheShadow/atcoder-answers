import collections

n = int(input())
t = [int(input()) for _ in range(n)]

t.sort()
score = [0] * (n + 1)
for i in range(n):
    score[i + 1] = score[i] + t[i]

c = collections.Counter(t)
ans = 1
for i in range(n):
    ans *= c[t[i]]
    ans %= 10 ** 9 + 7
    c[t[i]] -= 1

print(sum(score))
print(ans)