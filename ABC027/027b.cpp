#include<iostream>
#include<vector>
#include<numeric>

int solve(int n, std::vector<int> a) {
    int sum = std::accumulate(a.begin(), a.end(), 0);
    int len = a.size();
    if (sum % len != 0) return -1;

    int avg = sum / len;
    int ans = 0;
    for (int i = 0; i < n - 1; i++) {
        int left = 0;
        for (int j = 0; j <= i; j++) left += a[j];
        if (left != avg * (i + 1)) ans++;
    }
    return ans;
}

int main(){
    int n;
    std::cin >> n;
    std::vector<int> a(n);
    for (int i = 0; i < n; i++) std::cin >> a[i];
    std::cout << solve(n, a) << std::endl;
}

