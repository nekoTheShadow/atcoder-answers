#include<iostream>
#include<string>
#include<algorithm>
#include<vector>

int n, q;
std::string s;
std::vector<char> t, d;

int abs(int x) {
    return x >= 0 ? x : x * -1;
}

bool goal(int cur) {
    for (int i = 0; i < q; i++) {
        if (s[cur] == t[i]) {
            cur = (d[i] == 'L') ? cur - 1 : cur + 1;
        }
        if (cur < 0 || n <= cur) {
            return false;
        }
    }
    return true;
}

int bsearch(int ok, int ng) {
    while (std::abs(ok - ng) > 1) {
        int mi = (ok + ng) / 2;
        if (goal(mi)) {
            ok = mi;
        } else {
            ng = mi;
        }
    } 
    return ok;
}


int main() {
    std::cin >> n >> q;
    std::cin >> s;

    t.resize(q);
    d.resize(q);
    for (int i = 0; i < q; i++) {
        std::cin >> t[i] >> d[i];
    }

    int x = bsearch(-1, n);
    int y = bsearch(n, -1);
    int z = x - y + 1;
    std::cout << std::max(0, z) << std::endl;
}