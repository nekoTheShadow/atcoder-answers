#include <iostream>
#include <vector>

using namespace std;

int main()
{
	int n;
	cin >> n;

	vector<int> digits(n);
	for (int i = 0; i < n; i++) cin >> digits[i];

	int cnt = 0;
	bool has_even = false;
	while (true) {
		for (int i = 0; i < n; i++) {
			if (digits[i] % 2 == 0) {
				digits[i] = digits[i] / 2;
			} else {
				has_even = true;
				break;
			}
		}

		if (has_even) break;
		cnt++;
	}

	cout << cnt << endl;

    return 0;
}

