n = int(input())
a = [int(s) - 1 for s in input().split()]

visited = set()
for i in range(n):
    if i == a[a[i]]:
        visited.add(i)
        visited.add(a[i])

print(len(visited) // 2)