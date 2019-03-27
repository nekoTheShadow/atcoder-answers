#include<iostream>
#include<vector>
#include<climits>
#include<algorithm>

int main() {
    int n;
    int a, b;
    int m;
    std::cin >> n;
    std::cin >> a >> b;
    std::cin >> m;
    a--;
    b--;

    std::vector<std::vector<bool>> path(n, std::vector<bool>(n, false));
    for (int i = 0; i < m; i++) {
        int x, y;
        std::cin >> x >> y;
        x--;
        y--;
        path[x][y] = path[y][x] = true;
    }

    std::vector<std::vector<int>> score(n, std::vector<int>(n, 0));
    score[0][a] = 1;
    for (int i = 1; i < n; i++) {
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                if (!path[j][k]) continue;
                score[i][j] += score[i - 1][k];
                score[i][j] %= 1000000007;
            }
        }

        if (score[i][b] != 0) {
            std::cout << score[i][b] << std::endl;
            return 0;
        }
    }
}