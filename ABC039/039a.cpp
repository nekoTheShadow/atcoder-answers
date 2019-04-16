#include<iostream>

int main() {
    int a, b, c;
    std::cin >> a >> b >> c;
    int d = (a * b) + (a * c) + (b * c);
    std::cout << d * 2 << std::endl;
}