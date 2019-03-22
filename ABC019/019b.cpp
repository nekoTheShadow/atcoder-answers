#include<iostream>
#include<vector>
#include<string>
#include<sstream>

int main() {
    std::string s;
    std::cin >> s;
    
    std::stringstream ans;
    char word = s[0];
    int count = 1;
    for (int i = 1; i < s.size(); i++) {
        if (word == s[i]) {
            count++;
        } else {
            ans << word << count;
            word = s[i];
            count = 1;
        }
    }

    ans << word << count;
    std::cout << ans.str() << std::endl;
}