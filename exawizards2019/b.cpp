#include<iostream>

int main() {
    int n;
    std::cin >> n;
    
    int r = 0, b = 0;
    for (int i = 0; i < n; i++) {
        char c;
        std::cin >> c;
        if (c == 'R') {
            r++;
        } else {
            b++;
        } 
    }

    if (b < r) {
        std::cout << "Yes" << std::endl;
    } else {
        std::cout << "No" << std::endl;
    }
}