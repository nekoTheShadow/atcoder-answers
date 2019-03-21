#include<iostream>
#include<vector>

typedef long long ll;

int main() {
    ll n, k;
    std::cin >> n >> k;

    ll m = 1000000007;
    std::vector<ll> f(n + k), g(n + k), h(n + k);
    f[0] = f[1] = 1;
    g[0] = g[1] = 1;
    h[0] = h[1] = 1;
    for (ll i = 2; i < n + k; i++) {
        f[i] = f[i - 1] * i % m;
        g[i] = m - g[m % i] * (m / i) % m;
        h[i] = h[i - 1] * g[i] % m;
    }
    ll ans = f[n + k - 1] * (h[k]  * h[n - 1] % m) % m;
    std::cout << ans << std::endl;
}