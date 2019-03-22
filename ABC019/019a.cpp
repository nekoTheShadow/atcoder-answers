#include<iostream>
#include<algorithm>
#include<vector>

int main() {
    int a, b, c;
    std::cin >> a >> b >> c;
    std::vector<int> v = {a, b, c};
    std::sort(v.begin(), v.end());
    std::cout << v[1] << std::endl;
}