import itertools, operator, functools, sys

sys.setrecursionlimit(10 ** 9 + 7)

n = int(input())

amounts = [1]
amounts.extend(itertools.takewhile(lambda x: x <= n, itertools.accumulate(itertools.repeat(6), func=operator.mul)))
amounts.extend(itertools.takewhile(lambda x: x <= n, itertools.accumulate(itertools.repeat(9), func=operator.mul)))
amounts.sort(reverse=True)

@functools.lru_cache(maxsize=None)
def f(x):
    if x == 0:
        return 0
    
    count = float('inf')
    for amount in amounts:
        if x - amount >= 0:
            count = min(count, f(x - amount) + 1)
    return count

print(f(n))