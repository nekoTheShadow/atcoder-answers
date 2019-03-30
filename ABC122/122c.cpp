#include<iostream>
#include<string>
#include<vector>

int main() {
    int n, q;
    std::string s;
    std::cin >> n >> q >> s;
    
    std::vector<int> c(n + 1, 0);
    for (int i = 0; i < n - 1; i++) {
        if (s[i] == 'A' && s[i + 1] == 'C') c[i + 2] += 1;
        c[i + 3] += c[i + 2];
    }

    for (int i = 0; i < q; i++) {
        int l, r;
        std::cin >> l >> r;
        l--;
        r--;

        int ans = c[r + 1] - c[l];
        if (l > 0 && s[l - 1] == 'A' && s[l] == 'C') ans--;
        std::cout << ans << std::endl;
    }
}