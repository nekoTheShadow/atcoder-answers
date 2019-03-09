cache = {}

def f(x):
    if x == 0:
        return 2
    if x == 1:
        return 1
    
    if not x in cache:
        cache[x] = f(x - 1) + f(x - 2)
    return cache[x]


n = int(input())
print(f(n))