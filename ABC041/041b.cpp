#include <iostream>

typedef long long ll;

int main() {
    ll a, b, c;
    std::cin >> a >> b >> c;
    
    ll m = 1000000007;
    ll x = (((a % m) * (b % m) % m) * (c % m)) % m;
    std::cout << x << std::endl;
}