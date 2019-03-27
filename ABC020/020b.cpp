#include<iostream>
#include<string>

int main(int argc, char const *argv[]) {
    std::string a, b;
    std::cin >> a >> b;
    int ans = std::stoi(a + b) * 2;
    std::cout << ans << std::endl;
}
