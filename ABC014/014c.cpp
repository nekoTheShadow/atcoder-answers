#include<vector>
#include<iostream>

int main(int argc, char const *argv[]) {
    int len = 1000001;
    std::vector<int> imos(len, 0);

    int n;
    std::cin >> n;
    for (int i = 0; i < n; i++) {
        int a, b;
        std::cin >> a >> b;
        imos[a]++;
        imos[b + 1]--;
    }

    int max = imos[0];
    for (int i = 1; i < len; i++) {
        imos[i] += imos[i - 1]; 
        max = std::max(max, imos[i]);
    }
    std::cout << max << std::endl;
}
