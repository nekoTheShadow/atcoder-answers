#include<iostream>
#include<string>
#include<deque>

int main() {
    std::string s;
    int k;
    std::cin >> s;
    std::cin >> k;

    std::deque<char> q(s.begin(), s.end());

    for (int i = 0; i < k; i++) {
        char c = q.front();
        q.pop_front();
        q.push_back(c);
    }

    for (auto&& c : q) {
        std::cout << c;
    }
    std::cout << std::endl;
} 