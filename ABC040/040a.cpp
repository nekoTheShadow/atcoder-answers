#include<iostream>
#include<algorithm>

int main() {
    int n, x;
    std::cin >> n >> x;

    int a = x - 1;
    int b = n - x;

    std::cout << std::min(a, b) << std::endl;
}