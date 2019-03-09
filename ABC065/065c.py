MOD = 10 ** 9 + 7

def f(k):
    ans = 1
    for x in range(1, k + 1):
        ans = (ans * x) % MOD
    return ans % MOD

def main(n, m):
    if n == m:
        return 2 * f(n) * f(m) % MOD
    if abs(n - m) == 1:
        return f(n) * f(m) % MOD
    return 0

if __name__ == '__main__':
    n, m = map(int, input().split())
    print(main(n, m))
