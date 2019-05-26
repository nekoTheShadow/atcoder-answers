#include<iostream>
#include<vector>
#include<algorithm>

int main(){
    int n;
    std::cin >> n;
    std::vector<int> a(n), b(n);
    for (int i = 0; i < n; i++) std::cin >> a[i] >> b[i];

    for (int i = 0; i < n; i++) {
        int x = std::abs(a[i] - b[i]);
        std::cout << (x == 0 ? -1 : x) << std::endl;
    }
}