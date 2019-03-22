#include<iostream>
#include<vector>

int main() {
    int n, s, t, w;
    std::cin >> n >> s >> t >> w;
    std::vector<int> a(n);
    a[0] = 0;
    for (int i = 1; i < n; i++) std::cin >> a[i];

    int c = 0;
    for (int i = 0; i < n; i++) {
        w += a[i];
        if (s <= w && w <= t) c++;
    }

    std::cout << c << std::endl;
}