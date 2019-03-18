#include<iostream>

int main(int argc, char const *argv[]) {
    int a, b, c;
    std::cin >> a >> b >> c;
    auto ans = (a + b == c && a - b == c) ? "?" :
                a + b == c                ? "+" :
                              a - b == c  ? "-" : "!";
    std::cout << ans << std::endl;
}
