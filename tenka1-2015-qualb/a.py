import functools

@functools.lru_cache(maxsize=None)
def f(n):
    if n == 0 or n == 1:
        return 100
    if n == 2:
        return 200
    return f(n-1) + f(n-2) + f(n-3)

print(f(19))