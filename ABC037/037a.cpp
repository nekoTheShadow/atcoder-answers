#include <iostream>
#include<algorithm>

typedef unsigned long long ull;

int main() {
    ull a, b, c;
    std::cin >> a >> b >> c;
    std::cout << c / std::min(a, b) << std::endl;
}