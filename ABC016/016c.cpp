#include<iostream>
#include<vector>
#include<climits>

int main(int argc, char const *argv[]) {
    int n, m;
    std::cin >> n >> m;

    std::vector<std::vector<int>> c(n, std::vector<int>(n, 1000));
    for (int i = 0; i < n; i++) {
        c[i][i] = 0;
    }

    for (int i = 0; i < m; i++) {
        int a, b;
        std::cin >> a >> b;
        a--;
        b--;
        c[a][b] = c[b][a] = 1;
    }

    for (int z = 0; z < n; z++) {
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                c[x][y] = std::min(c[x][y], c[x][z] + c[z][y]);
            }
        }
    }

    for (int i = 0; i < n; i++) {
        int cnt = 0;
        for (int j = 0; j < n; j++) {
            if (c[i][j] == 2) {
                cnt++;
            }
        }
        std::cout << cnt << std::endl;
    }
}
