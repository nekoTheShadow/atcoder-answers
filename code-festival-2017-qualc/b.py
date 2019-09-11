import functools, operator

n = int(input())
a = list(map(int, input().split()))

d = functools.reduce(operator.mul, (2 if v % 2 == 0 else 1 for v in a), 1)
print(3 ** n - d)