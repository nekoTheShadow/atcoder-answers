import itertools

R, G, B, N = map(int, input().split())

count = 0

rmax = N // R
for r in range(rmax + 1):
    x = N - r * R
    gmax = x // G
    for g in range(gmax + 1):
        y = x - g * G
        if y >= 0 and y % B == 0:
            count += 1

print(count)
