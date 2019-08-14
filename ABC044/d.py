import math
import sys
import functools

sys.setrecursionlimit(10 ** 9)

@functools.lru_cache(None)
def f(b, n):
    if n < b:
        return n
    else:
        return f(b, n // b) + (n % b)

def main(n, s):
    if s == n:
        return n + 1

    if s > n:
        return -1

    for b in range(2, math.ceil(math.sqrt(n)) + 1):
        if f(b, n) == s:
            return b

    ans = float('inf')
    for p in range(1, math.ceil(math.sqrt(n)) + 1):
        if (n - s) % p == 0:
            b = (n - s) // p + 1
            if f(b, n) == s:
                ans = min(ans, b)

    return -1 if ans == float('inf') else ans

if __name__ == '__main__':
    n = int(input())
    s = int(input())
    print(main(n, s))
