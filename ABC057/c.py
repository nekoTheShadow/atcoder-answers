import math

if __name__ == '__main__':
    n = int(input())
    ans = float('inf')

    a = 1
    while a <= math.sqrt(n):
        if n % a == 0:
            b = n // a
            f = max(math.floor(math.log10(a) + 1), math.floor(math.log10(b) + 1))
            ans = min(f, ans)

        a += 1

    print(ans)
