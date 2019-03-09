import sys
sys.setrecursionlimit(10 ** 7)

n, z, w = map(int, input().split())
a = list(map(int, input().split()))

cache = {}

def f(idx, turn, x, y):
    if idx == n:
        return abs(x - y)
    
    key = (idx, turn)
    if key in cache:
        return cache[key]

    if turn == 0:
        ans = -float('inf')
        for i in range(idx, n):
            ans = max(ans, f(i + 1, 1 - turn, a[i], y))
    else:
        ans = float('inf')
        for i in range(idx, n):
            ans = min(ans, f(i + 1, 1 - turn, x, a[i]))

    cache[key] = ans
    return cache[key]

print(f(0, 0, z, w))