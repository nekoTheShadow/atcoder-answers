#include<iostream>
#include<vector>
#include<algorithm>

int main() {
    int n, k;
    std::cin >> n >> k;

    int t = n;
    std::vector<int> v;
    for (int i = 2; i * i <= n; i++) {
        while (t % i == 0) {
            v.push_back(i);
            t = t / i;
        }
    }

    if (t > 1) {
        v.push_back(t);
    }
    std::sort(v.begin(), v.end());

    if (v.size() < k) {
        std::cout << -1 << std::endl;
    } else {
        for (int i = 0; i < k - 1; i++) {
            std::cout << v[i] << " ";
        }
        
        int x = 1;
        for (int i = k - 1; i < v.size(); i++) {
            x *= v[i];
        }
        std::cout << x << std::endl;
    }
}