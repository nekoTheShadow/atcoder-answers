#include<iostream>
#include<vector>

typedef long long ll;

int main(){
    ll n;
    std::cin >> n;
    std::vector<ll> a(n), b(n);
    for (int i = 0; i < n; i++) std::cin >> a[i] >> b[i];
    for (int i = 0; i < n; i++) std::cout << a[i] * b[i] << std::endl;
}