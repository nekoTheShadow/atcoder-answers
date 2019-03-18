#include<iostream>
#include<vector>
#include<stack>

int main(int argc, char const *argv[]) {
    int n, k;
    std::cin >> n >> k;

    std::vector<std::vector<int>> t(n, std::vector<int>(k));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < k; j++) std::cin >> t[i][j];
    }

    bool err = false;
    std::stack<std::vector<int>> stack;
    for (int i = 0; i < k; i++) {
        stack.push(std::vector<int>{ i });
    }

    while (!stack.empty()) {
        auto idxs = stack.top();
        stack.pop();

        if (idxs.size() == n) {
            int x = t[0][idxs[0]];
            for (int i = 1; i < n; i++) {
                x = x ^ t[i][idxs[i]];
            }

            if (x == 0) {
                err = true;
                goto EXIT;
            }
        } else {
            for (int i = 0; i < k; i++) {
                auto nxts = std::vector<int>(idxs);
                nxts.push_back(i);
                stack.push(nxts);
            }
        }
    }

EXIT:
    auto ans = err ? "Found" : "Nothing";
    std::cout << ans << std::endl;
    return 0;
}
