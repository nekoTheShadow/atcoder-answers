import collections

n = int(input())
a = list(map(int, input().split()))

b = collections.deque()
for i, v in enumerate(a):
    if i % 2 == 0:
        b.append(v)
    else:
        b.appendleft(v)

ans = ' '.join(map(str, b if n % 2 == 0 else reversed(b)))
print(ans)