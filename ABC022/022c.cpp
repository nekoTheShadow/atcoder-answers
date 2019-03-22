#include<iostream>
#include<vector>
#include<climits>
#include<algorithm>

int main() {
    int n, m;
    std::cin >> n >> m;
    std::vector<std::vector<int>> c(n, std::vector<int>(n, INT_MAX));
    std::vector<int> d(n, INT_MAX);
    for (int i = 0; i < m; i++) {
        int u, v, l;
        std::cin >> u >> v >> l;
        u--;
        v--;

        if (u == 0) {
            d[v] = l;
        } else {
            c[u][v] = c[v][u] = l;
        }
    }

    for (int k = 0; k < n; k++) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (c[i][k] != INT_MAX && c[k][j] != INT_MAX) {
                    c[i][j] = std::min(c[i][j], c[i][k] + c[k][j]);
                }
            }
        }
    }

    int ans = INT_MAX;
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            if (d[i] == INT_MAX || d[j] == INT_MAX || c[i][j] == INT_MAX) continue;
            ans = std::min(ans, d[i] + c[i][j] + d[j]); 
        }
    }
    
    std::cout << (ans == INT_MAX ? -1 : ans) << std::endl;
}