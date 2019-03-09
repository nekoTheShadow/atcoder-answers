a, b, c, x, y = map(int, input().split())

i = 0
ans = float('inf')
while i // 2 <= max(x, y):
    price = a * max(0, x - i // 2) + b * max(0, y - i // 2) + c * i
    ans = min(price, ans)
    i += 2

print(ans)