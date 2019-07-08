import sys

sys.setrecursionlimit(2 ** 31 - 1)

n = int(input())
a = list(map(int, input().split()))

def f(x):
    while x < n and a[x] == 0:
        x += 1
    
    if n <= x:
        return 0
    
    while x < n - 1 and a[x] <= a[x + 1]:
        x += 1
    
    return f(x + 1) + 1

print(f(0))
