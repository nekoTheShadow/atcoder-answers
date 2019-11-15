b = list(map(int, input().split()))
n = int(input())
a = [int(input()) for _ in range(n)]

d = {b[i] : i for i in range(len(b))}
def f(x):
    stack = []
    while x > 0:
        stack.append(d[x % 10])
        x //= 10
    y = 0
    while stack:
        y = y * 10 + stack.pop()
    return y

c = list(map(f, a))
for i in sorted(range(n), key=lambda x: c[x]):
    print(a[i])