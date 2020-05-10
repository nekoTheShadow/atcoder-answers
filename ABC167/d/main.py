n, k = map(int, input().split())
a = list(map(lambda x: int(x) - 1, input().split()))

history = []
visited = set()
cur = 0
for i in range(k):
    if cur in visited:
        x = history.index(cur)
        y = len(history) - x
        print(history[x+(k-x)%y] + 1)
        exit()

    visited.add(cur)
    history.append(cur)
    cur = a[cur]

print(a[history[k-1]]+1)