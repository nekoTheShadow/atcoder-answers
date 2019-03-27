#include<iostream>

int main(int argc, char const *argv[]) {
    int q;
    std::cin >> q;
    auto ans = q == 1 ? "ABC" : "chokudai";
    std::cout << ans << std::endl;
}
