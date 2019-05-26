#include<iostream>
#include<vector>
#include<algorithm>
#include<numeric>

int main(){
    int n;
    std::cin >> n;
    std::vector<long> a(n), b(n);
    for (int i = 0; i < n; i++) std::cin >> a[i] >> b[i];

    int t;
    long v = 0;
    for (int i = 0; i < n; i++) {
        long w = a[i] * b[i];
        if (v < w) {
            t = i;
            v = w;
        }
    }

    bool ok = true;
    for (int i = 0; i < n; i++) {
        if (i == t) continue;
        if ((a[t] + b[i] - 1) / b[i] <= (a[i] + b[t] - 1) / b[t]) ok = false;
    }

    std::cout << (ok ? t + 1 : -1) << std::endl;
}