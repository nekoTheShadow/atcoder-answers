#include<iostream>
#include<algorithm>

int main() {
    int n;
    std::cin >> n;

    int ans = n;
    for (int i = 1; i * i <= n; i++) {
        int j = n / i;
        int k = n - i * j;
        ans = std::min(ans, std::abs(i - j) + k);
    }

    std::cout << ans << std::endl;
}