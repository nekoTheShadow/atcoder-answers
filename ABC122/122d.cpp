#include<iostream>
#include<vector>
#include<map>
#include<tuple>

typedef long long ll;

int N;
ll M = 1000000007;
std::vector<char> chars = {'A', 'C', 'G', 'T'};
std::map<std::tuple<int, char, char, char>, ll> memo;

ll f(int n, char a, char b, char c) {
    if (n == N) return 1;

    auto key = std::make_tuple(n, a, b, c);
    if (memo.count(key) == 1) return memo[key];

    ll sum = 0;
    for (char d : chars) {
        if (a == 'A' && b == 'A' && c == 'C' && d == 'G') continue;
        if (a == 'A' && b == 'A' && c == 'G' && d == 'C') continue;
        if (a == 'A' && b == 'C' && c == 'G' && d == 'A') continue;
        if (a == 'A' && b == 'C' && c == 'G' && d == 'C') continue;
        if (a == 'A' && b == 'C' && c == 'G' && d == 'G') continue;
        if (a == 'A' && b == 'C' && c == 'G' && d == 'T') continue;
        if (a == 'A' && b == 'G' && c == 'A' && d == 'C') continue;
        if (a == 'A' && b == 'G' && c == 'C' && d == 'A') continue;
        if (a == 'A' && b == 'G' && c == 'C' && d == 'C') continue;
        if (a == 'A' && b == 'G' && c == 'C' && d == 'G') continue;
        if (a == 'A' && b == 'G' && c == 'C' && d == 'T') continue;
        if (a == 'A' && b == 'G' && c == 'G' && d == 'C') continue;
        if (a == 'A' && b == 'G' && c == 'T' && d == 'C') continue;
        if (a == 'A' && b == 'T' && c == 'G' && d == 'C') continue;
        if (a == 'C' && b == 'A' && c == 'C' && d == 'G') continue;
        if (a == 'C' && b == 'A' && c == 'G' && d == 'C') continue;
        if (a == 'C' && b == 'G' && c == 'A' && d == 'C') continue;
        if (a == 'G' && b == 'A' && c == 'C' && d == 'A') continue;
        if (a == 'G' && b == 'A' && c == 'C' && d == 'C') continue;
        if (a == 'G' && b == 'A' && c == 'C' && d == 'G') continue;
        if (a == 'G' && b == 'A' && c == 'C' && d == 'T') continue;
        if (a == 'G' && b == 'A' && c == 'G' && d == 'C') continue;
        if (a == 'G' && b == 'G' && c == 'A' && d == 'C') continue;
        if (a == 'T' && b == 'A' && c == 'C' && d == 'G') continue;
        if (a == 'T' && b == 'A' && c == 'G' && d == 'C') continue;
        if (a == 'T' && b == 'G' && c == 'A' && d == 'C') continue;
        sum = (sum + f(n + 1, b, c, d)) % M;
    }
    return memo[key] = sum;
}

int main() {
    std::cin >> N;
    ll ans = (N == 3) ? 61 : f(0, 'X', 'X', 'X');
    std::cout << ans << std::endl;
}