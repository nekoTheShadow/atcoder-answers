#include<iostream>
#include<vector>
#include<string>
#include<sstream>

int main() {
    std::string s;
    std::cin >> s;

    int len = 12;
    std::vector<std::string> colors = {"W", "B", "W", "B", "W", "W", "B", "W", "B", "W", "B", "W"};
    std::vector<std::string> codes = {"Do", "Do", "Re", "Re", "Mi", "Fa", "Fa", "So", "So", "La", "La", "Si"};

    for (int i = 0; i < len; i++) {
        std::stringstream t;
        for (int j = 0; j < 20; j++) {
            t << colors[(i + j) % len];
        }

        if (s == t.str()) {
            std::cout << codes[i] << std::endl;
            break;
        }
    }
}