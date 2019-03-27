#include <iostream>
#include <vector>
#include <numeric>
#include <algorithm>

int main() {
    int n;
    std::cin >> n;
    
    std::vector<int> a(n);
    for(int i = 0; i < n; i++) std::cin >> a[i];

    std::vector<int> b(n);
    std::iota(b.begin(), b.end(), 0);

    std::sort(b.begin(), b.end(), [&](int i1, int i2){ return a[i1] > a[i2]; });
    for (int i : b) std::cout << i + 1 << std::endl;
}