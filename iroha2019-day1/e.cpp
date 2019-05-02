#include<iostream>
#include<vector>
#include<algorithm>

typedef long long ll;

int main() {
    ll n, a, b;
    std::cin >> n >> a >> b;

    std::vector<ll> d(b);
    for (ll i = 0; i < b; i++) {
        std::cin >> d[i];
    }
    d.push_back(0);
    d.push_back(n + 1);
    std::sort(d.begin(), d.end());

    n -= b;
    for (ll i = 0; i < b + 1; i++) {
        n -= (d[i + 1] - d[i] - 1) / a;
    }

    std::cout << n << std::endl;
}