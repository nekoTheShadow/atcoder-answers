#include<iostream>
#include<vector>
#include<set>
#include<algorithm>

int main(){
    int n;
    std::cin >> n;
    std::vector<int> a(n), b(n);
    for (int i = 0; i < n; i++) {
        std::cin >> a[i] >> b[i];
    }

    std::set<int> s;
    for (int i = 1; i * i <= a[0]; i++) {
        if (a[0] % i == 0) {
            s.insert(i);
            s.insert(a[0] / i);
        }
    }
    for (int i = 1; i * i <= b[0]; i++) {
        if (b[0] % i == 0) {
            s.insert(i);
            s.insert(b[0] / i);
        }
    }

    int max = 0;
    for (auto&& x : s) {
        bool all = true;
        for (int i = 0; i < n; i++) {
            if (a[i] % x != 0 && b[i] % x != 0) {
                all = false;
            }
        }

        if (all) {
            max = std::max(max, x);
        }
    }

    std::cout << max << std::endl;
}