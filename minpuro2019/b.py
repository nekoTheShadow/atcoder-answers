counts = [0] * 4
for _ in range(3):
    a, b = map(int, input().split())
    counts[a - 1] += 1
    counts[b - 1] += 1

even = 0
for count in counts:
    if count % 2 == 0: even += 1

print('YES' if even == 2 else 'NO')