import functools, sys

x, y = map(int, input().split())

mod = 10 ** 9 + 7

def modpow(x, y):
    z = 1
    while y > 0:
        if y % 2 == 0:
            x = (x * x) % mod
            y //= 2
        else:
            z = (z * x) % mod
            y -= 1
    return z

def modinv(x):
    return modpow(x, mod - 2)

def c(n, r):
    if n == r or r == 0:
        return 1

    fct = [None] * (n + 1)
    fct[0] = 1
    for i in range(1, n + 1):
        fct[i] = fct[i - 1] * i % mod
    return fct[n] * (modinv(fct[n - r]) * modinv(fct[r]) % mod) % mod

def f():
    if 2 * y - x < 0 or (2 * y - x) % 3 != 0:
        return 0
    a = (2 * y - x) // 3
    b = y - 2 * a
    if b < 0:
        return 0
    
    return c(a + b, a)

print(f())