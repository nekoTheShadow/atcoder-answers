import itertools

a, b, c = map(int, input().split())
print(min(map(sum, itertools.combinations((a, b, c), 2))))
