#include<iostream>
#include<vector>

typedef long long ll;

int main() {
    int n, k;
    std::cin >> n >> k;

    std::vector<ll> a(n);
    for (int i = 0; i < n; i++) std::cin >> a[i];

    ll sum = 0;
    for (int i = 0; i < k; i++) sum += a[i];

    ll ans = sum;
    for (int s = 0, t = k; t < n; s++, t++) {
        sum -= a[s];
        sum += a[t];
        ans += sum;
    }

    std::cout << ans << std::endl;
}