#include<iostream>
#include<sstream>
 
using namespace std;
 
string format(int a, int b, int c) {
    stringstream ss;
    ss << a << " " << b << " " << c;
    return ss.str();
}
 
string solve(int n, int y) {
    for (int a = 0; a <= n; a++) {
        for (int b = 0; a + b <= n; b++) {
            int c = n - a - b;
            if (a * 10000 + b * 5000 + c * 1000 == y) return format(a, b, c);
        }
    }
    return format(-1, -1, -1);
}
 
 
int main()
{
    int n, y;
    cin >> n >> y;
 
    string answer = solve(n, y);
    cout << answer << endl;
    return 0;
}
 