a, b = map(int, input().split())

c = 1
tap = 0
while c < b:
    c = c - 1 + a
    tap += 1
print(tap) 