#include<iostream>
#include<cmath>

int main() {
    double x;
    std::cin >> x;

    double y = std::sqrt(std::sqrt(x));
    std::cout << y << std::endl;
}