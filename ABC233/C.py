n, x = map(int, input().split())
a = []
for _ in range(n):
    a.append(list(map(int, input().split()))[1:])

def f(p, tot):
    if p == n:
        return 1 if tot == x else 0
    return sum(f(p+1, tot*v) for v in a[p])

print(f(0, 1))
