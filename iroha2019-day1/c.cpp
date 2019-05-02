#include<iostream>

int main() {
    int n;
    std::cin >> n;

    for (int i = n - 8 + 1; i <= n; i++) {
        std::cout << i << std::endl;
    }
}