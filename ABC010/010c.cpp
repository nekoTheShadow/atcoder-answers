#include<iostream>
#include<cmath>

int main(int argc, char const *argv[])
{
    double txa, tya, txb, tyb, t, v;
    int n;
    std::cin >> txa >> tya >> txb >> tyb >> t >> v;
    std::cin >> n;
    
    bool ok = false;
    for (int i = 0; i < n; i++)
    {
        double x, y;
        std::cin >> x >> y;

        double d1 = std::sqrt(std::pow(txa - x, 2) + std::pow(tya - y, 2));
        double d2 = std::sqrt(std::pow(txb - x, 2) + std::pow(tyb - y, 2));
        if (d1 + d2 <= t * v) 
        {
            ok = true;
        }
    }

    auto ans = ok ? "YES" : "NO";
    std::cout << ans << std::endl;
    return 0;
}

