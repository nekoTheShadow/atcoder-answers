
MOD = 998244353
MAX = 2*10**5+1
fac = [0] * MAX
finv = [0] * MAX
inv = [0] * MAX
fac[0] = fac[1] = 1
finv[0] = finv[1] = 1
inv[1] = 1
for i in range(2, MAX):
    fac[i] = fac[i - 1] * i % MOD
    inv[i] = MOD - inv[MOD%i] * (MOD // i) % MOD
    finv[i] = finv[i - 1] * inv[i] % MOD

def C(n, k):
    if n < k: return 0
    if n < 0 or k < 0: return 0
    return fac[n] * (finv[k] * finv[n - k] % MOD) % MOD


n, m, k = map(int, input().split())
ans = 0
for x in range(k+1):
    ans += C(n - 1, x) * m * pow(m-1, n-x-1, mod=MOD)
    ans %= MOD
print(ans)