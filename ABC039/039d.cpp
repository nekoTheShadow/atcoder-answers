#include<iostream>
#include<vector>
#include<string>

int main() {
    int h, w;
    std::cin >> h >> w;

    // s1: 1回だけ変換された画像
    // s2: s1から変換前に戻した画像
    // s3: s2から変換した画像
    std::vector<std::string> s1(h), s2(h), s3(h);
    for (int i = 0; i < h; i++) {
        std::string s;
        std::cin >> s;
        s1[i] = s;
        s2[i] = s;
        s3[i] = s;
    }

    for (int x = 0; x < h; x++) {
        for (int y = 0; y < w; y++) {
            if (s1[x][y] == '#') {
                continue;
            }

            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    int nx = x + dx, ny = y + dy;
                    if (0 <= nx && nx < h && 0 <= ny && ny < w) {
                        s2[nx][ny] = '.';
                    }
                }
            }
        }
    }

    for (int x = 0; x < h; x++) {
        for (int y = 0; y < w; y++) {
            bool black = false;
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    int nx = x + dx, ny = y + dy;
                    if (0 <= nx && nx < h && 0 <= ny && ny < w && s2[nx][ny] == '#') {
                        black = true;
                        break;
                    }
                }
            }

            s3[x][y] = black ? '#' : '.';
        }
    }

    if (s1 == s3) {
        std::cout << "possible" << std::endl;
        for (auto&& row : s2) {
            std::cout << row << std::endl;
        }
    } else {
        std::cout << "impossible" << std::endl;
    }
}