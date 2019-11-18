n = int(input())

paths = set()
for i in range(1, n + 1):
    for j in range(i + 1, n + 1):
        paths.add((i, j))

if n % 2 == 0:
    for i in range(1, n + 1):
        path = (min(i, n - i + 1), max(i, n - i + 1))
        paths.discard(path)
else:
    for i in range(1, n + 1):
        path = (min(i, n - i), max(i, n - i))
        paths.discard(path)

print(len(paths))
for path in paths:
    print('{0} {1}'.format(path[0], path[1]))