n = int(input())
a = [0, *map(int, input().split()), 0]

total = sum(abs(a[i] - a[i + 1]) for i in range(0, n + 1))
for i in range(1, n + 1):
    print(total - abs(a[i - 1] - a[i]) - abs(a[i] - a[i + 1]) + abs(a[i - 1] - a[i + 1]))
