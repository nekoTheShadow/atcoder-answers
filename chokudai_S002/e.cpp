#include<iostream>
#include<vector>
#include<algorithm>

typedef long long ll;

int main(){
    ll n;
    std::cin >> n;
    std::vector<ll> a(n), b(n);
    for (int i = 0; i < n; i++) std::cin >> a[i] >> b[i];

    ll sum = 0;
    for (int i = 0; i < n; i++) sum += std::min(a[i] / 2, b[i]);
    std::cout << sum << std::endl;
}