#include<iostream>
#include<vector>

int main() {
    int n, q;
    std::cin >> n >> q;

    std::vector<int> a(n, 0);
    int l, r, t;
    for (int i = 0; i < q; i++) {
        std::cin >> l >> r >> t;
        l--;
        r--;
        for (int j = l; j <= r; j++) {
            a[j] = t;
        }
    }

    for (int v : a) std::cout << v << std::endl;
}