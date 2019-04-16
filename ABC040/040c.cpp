#include<iostream>
#include<vector>
#include<numeric>
#include<algorithm>
#include<cmath>

typedef long long ll;

int main() {
    int n;
    std::cin >> n;

    std::vector<ll> a(n);
    for (int i = 0; i < n; i++) std::cin >> a[i];

    ll inf = std::accumulate(a.begin(), a.end(), 0LL);
    std::vector<ll> dp(n, inf);
    dp[0] = 0;
    for (int i = 0; i < n; i++) {
        if (i + 1 < n) dp[i + 1] = std::min(dp[i + 1], dp[i] + std::abs(a[i + 1] - a[i]));
        if (i + 2 < n) dp[i + 2] = std::min(dp[i + 2], dp[i] + std::abs(a[i + 2] - a[i]));
    }
    std::cout << dp[n - 1] << std::endl;
}