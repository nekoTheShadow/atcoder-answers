#include<iostream>
#include<vector>
#include<utility>
#include<numeric>
#include<algorithm>

struct unionfind {
    std::vector<int> parents;
    std::vector<int> sizes;

    unionfind(int n) {
        for (int i = 0; i < n; i++) {
            parents.push_back(i);
            sizes.push_back(1);
        }
    }

    int find(int x) {
        if (x == parents[x]) {
            return x;
        } else {
            return parents[x] = find(parents[x]);
        }
    }

    void unite(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return ;

        if (sizes[x] < sizes[y]) std::swap(x, y);
        parents[y] = x;
        sizes[x] += sizes[y];
    }

    int size(int x) {
        return sizes[find(x)];
    }
};


int main() {
    int n, m;
    std::cin >> n >> m;

    std::vector<int> a(m), b(m), y(m);
    for (int i = 0; i < m; i++) {
        std::cin >> a[i] >> b[i] >> y[i];
        a[i]--;
        b[i]--;
    }

    int q;
    std::cin >> q;

    std::vector<int> v(q), w(q);
    for (int i = 0; i < q; i++) {
        std::cin >> v[i] >> w[i];
        v[i]--;
    }

    std::vector<int> idx1(m), idx2(q), ans(q);
    std::iota(idx1.begin(), idx1.end(), 0);
    std::iota(idx2.begin(), idx2.end(), 0);
    std::sort(idx1.begin(), idx1.end(), [&](int i1, int i2){ return y[i1] > y[i2]; });
    std::sort(idx2.begin(), idx2.end(), [&](int i1, int i2){ return w[i1] > w[i2]; });

    auto uf = unionfind(n);
    int j = 0;
    for (int i = 0; i < q; i++) {
        while (j < m && y[idx1[j]] > w[idx2[i]]) {
            uf.unite(a[idx1[j]], b[idx1[j]]);
            j++;
        }
        ans[idx2[i]] = uf.size(v[idx2[i]]);
    }

    for (auto&& e : ans) std::cout << e << std::endl;
}