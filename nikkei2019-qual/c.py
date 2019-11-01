n = int(input())
dishes = []
for _ in range(n):
    a, b = map(int, input().split())
    dishes.append((a, b, a + b))
dishes.sort(key=lambda dish: dish[2], reverse=True)

x = y = 0
for i in range(n):
    a, b, _ = dishes[i]
    if i % 2 == 0:
        x += a
    else:
        y += b

print(x - y)