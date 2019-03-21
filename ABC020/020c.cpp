#include<iostream>
#include<vector>
#include<climits>
#include<utility>
#include<algorithm>

typedef long long ll;


ll solve(ll a, ll h, ll w, ll sx, ll sy, ll gx, ll gy, std::vector<std::vector<char>> s) {
    std::vector<std::vector<ll>> cost(h, std::vector<ll>(w, LLONG_MAX));
    cost[sx][sy] = 0;
    std::vector<std::pair<ll, ll>> stack = {std::make_pair(sx, sy)};
    std::vector<std::pair<ll, ll>> diffs = {
        std::make_pair(0, 1),
        std::make_pair(0, -1),
        std::make_pair(1, 0),
        std::make_pair(-1, 0),
    };
    ll min = LLONG_MAX;

    while (!stack.empty()) {
        auto head = stack.back();
        stack.pop_back();
        ll x = head.first, y = head.second;

        if (x == gx && y == gy) {
            min = std::min(min, cost[x][y]);
            continue;
        } 

        for (auto& diff : diffs) {
            ll nx = x + diff.first, ny = y + diff.second;
            if (!(0 <= nx && nx < h && 0 <= ny && ny < w)) continue;
            
            ll c = (s[nx][ny] == '#') ? a : 1;
            if (cost[nx][ny] <= cost[x][y] + c)  continue;

            cost[nx][ny] = cost[x][y] + c;
            stack.push_back(std::make_pair(nx, ny));
        }
    }

    return min;
}


int main(int argc, char const *argv[]) {
    ll h, w, t;
    std::cin >> h >> w >> t;

    std::vector<std::vector<char>> s(h, std::vector<char>(w));
    ll sx, sy, gx, gy;
    for (ll x = 0; x < h; x++) {
        for (ll y = 0; y < w; y++) {
            char ch;
            std::cin >> ch;
            if (ch == 'S') {
                sx = x;
                sy = y;
            } 
            if (ch == 'G') {
                gx = x;
                gy = y;
            }
            s[x][y] = ch;
        }
    }

    ll lo = 1;
    ll hi = 1000000000;
    while (hi - lo != 1) {
        ll mi = (lo + hi) / 2;
        if (solve(mi, h, w, sx, sy, gx, gy, s) <= t) {
            lo = mi;
        } else {
            hi = mi;
        }
    }

    std::cout << lo << std::endl;
}

