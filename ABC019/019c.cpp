#include<iostream>
#include<set>

int main() {
    int n;
    std::cin >> n;
    std::set<int> s;
    for (int i = 0; i < n; i++) {
        int a;
        std::cin >> a;
        while (a % 2 == 0) a /= 2;
        s.insert(a);
    }

    std::cout << s.size() << std::endl;
}