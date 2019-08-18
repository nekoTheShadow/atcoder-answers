n = int(input())
a = list(map(int, input().split()))

# B0を決定する
# B0 = a0 + a1 + .. - (a1 + a3 + ... ) * 2
b0 = sum(a)
for i in range(1, n, 2):
    b0 -= a[i] * 2

# a0 = b0 / 2 + b1 / 2
#   <=> b1 = a0 * 2 - b0
b = [None] * n
b[0] = b0
for i in range(1, n):
    b[i] = a[i - 1] * 2 - b[i - 1]

print(' '.join(map(str, b)))

