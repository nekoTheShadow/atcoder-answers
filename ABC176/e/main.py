import collections, itertools

h, w, m = map(int, input().split())
bombs = set(tuple(map(int, input().split())) for _ in range(m))

a = collections.Counter(x for x, y in bombs)
b = collections.Counter(y for x, y in bombs)
a_max_val = max(a.values())
b_max_val = max(b.values())
a_max_list = [k for k, v in a.items() if v == a_max_val]
b_max_list = [k for k, v in b.items() if v == b_max_val]

for a_max, b_max in itertools.product(a_max_list, b_max_list):
    if not (a_max, b_max) in bombs:
        print(a_max_val + b_max_val)
        exit(0)

print(a_max_val + b_max_val - 1)