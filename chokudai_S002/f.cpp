#include<iostream>
#include<vector>
#include<algorithm>
#include<tuple>
#include<set>

typedef long long ll;

int main(){
    ll n;
    std::cin >> n;
    std::vector<ll> a(n), b(n);
    for (int i = 0; i < n; i++) std::cin >> a[i] >> b[i];

    std::set<std::tuple<ll, ll>> c;
    for (int i = 0; i < n; i++) {
        int x = std::min(a[i], b[i]);
        int y = std::max(a[i], b[i]);
        c.insert(std::make_tuple(x, y));
    }

    std::cout << c.size() << std::endl;
}