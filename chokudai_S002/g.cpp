#include<iostream>
#include<vector>
#include<algorithm>

int gcd(int x, int y) {
    return x % y == 0 ? y : gcd(y, x % y);
}

int main(){
    int n;
    std::cin >> n;
    std::vector<int> a(n), b(n);
    for (int i = 0; i < n; i++) std::cin >> a[i] >> b[i];

    for (int i = 0; i < n; i++) {
        int x = std::max(a[i], b[i]);
        int y = std::min(a[i], b[i]);
        std::cout << gcd(x, y) << std::endl;
    }
}