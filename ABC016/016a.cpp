#include<iostream>

int main(int argc, char const *argv[]) {
    int m, d;
    std::cin >> m >> d;
    auto ans = m % d == 0 ? "YES" : "NO";
    std::cout << ans << std::endl;
    return 0;
}
