#include<iostream>
#include<vector>
#include<map>

int main() {
    int n;
    std::cin >> n;
    std::vector<int> a(n);
    for (int i = 0; i < n; i++) std::cin >> a[i];

    std::map<int, bool> before;

    int ans = 0;
    for (int i = 0; i < n; i++) {
        if (before.count(a[i]) == 1) ans++;
        before[a[i]] = true;
    }

    std::cout << ans << std::endl;
}