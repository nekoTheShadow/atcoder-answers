#include<iostream>

typedef long long ll;

int main() {
    ll n;
    std::cin >> n;

    ll d = 0;
    for (ll x = n; x > 0; x /= 2) d++;

    ll x = 1;
    bool takahashi = true;
    while (true) {
        if (d % 2 == 0) {
            if (takahashi) {
                x = x * 2;
            } else {
                x = x * 2 + 1;
            }
        } else {
            if (takahashi) {
                x = x * 2 + 1;
            } else {
                x = x * 2;
            }
        }

        if (n < x) {
            std::cout << (takahashi ? "Aoki" : "Takahashi") << std::endl;
            return 0;
        } 

        takahashi = !takahashi;
    }
}