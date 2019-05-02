#include<iostream>
#include<vector>
#include<algorithm>

int main() {
    int n, x, y;
    std::cin >> n >> x >> y;

    std::vector<int> a(n);
    for (int i = 0; i < n; i++) std::cin >> a[i];

    std::sort(a.begin(), a.end());
    std::reverse(a.begin(), a.end());

    for (int i = 0; i < n; i++) {
        if (i % 2 == 0) {
            x += a[i];
        } else {
            y += a[i];
        }
    }

    auto ans = x > y ? "Takahashi" :
               x < y ? "Aoki"      :
                       "Draw"      ;
    std::cout << ans << std::endl;
}