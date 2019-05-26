#include<iostream>
#include<vector>
#include<algorithm>
#include<limits>

typedef long long ll;

int main(){
    ll n;
    std::cin >> n;
    std::vector<ll> a(n), b(n);
    for (int i = 0; i < n; i++) std::cin >> a[i] >> b[i];

    ll max = std::numeric_limits<ll>::min();
    for (int i = 0; i < n; i++) max = std::max(max, a[i] + b[i]);
    std::cout << max << std::endl;
}