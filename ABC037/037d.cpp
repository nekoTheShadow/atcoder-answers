#include<iostream>
#include<vector>

typedef long long ll;

int h, w;
std::vector<std::vector<ll>> a, v;
ll divisor = 1000000007LL;
std::vector<int> dx = {1, -1, 0,  0};
std::vector<int> dy = {0,  0, 1, -1};

ll f(int x, int y) {
    if (v[x][y] != -1) {
        return v[x][y];
    }

    ll z = 1LL;
    for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];
        if (0 <= nx && nx < h && 0 <= ny && ny < w && a[x][y] < a[nx][ny]) {
            z += f(nx, ny);
            z %= divisor;
        }
    }

    v[x][y] = z;
    return z;
}

int main() {
    std::cin >> h >> w;

    a.assign(h, std::vector<ll>(w));
    for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) std::cin >> a[i][j];
    }

    v.assign(h, std::vector<ll>(w, -1));

    // for (int i = 0; i < h; i++) {
    //     a.push_back(std::vector<ll>(w));
    //     v.push_back(std::vector<ll>(w));
    //     for (int j = 0; j < w; j++) {
    //         std::cin >> a[i][j];
    //         v[i][j] = -1;
    //     }
    // }
    
    ll ans = 0LL;
    for (int x = 0; x < h; x++) {
        for (int y = 0; y < w; y++) {
            ans += f(x, y);
            ans %= divisor;
        }
    }
    std::cout << ans << std::endl;
}