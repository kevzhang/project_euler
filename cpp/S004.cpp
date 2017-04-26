#include <iostream>

using namespace std;

static const int LIMIT = 1000;

bool is_palindrome(int n) {
	int reversed = 0, remaining = n;
	while (remaining > 0) {
		reversed = 10 * reversed + remaining % 10;
		remaining /= 10;
	}
	return reversed == n;
}

int main() {
	int max_pal = -1;
	for (int left = 100; left < LIMIT; left++) {
		for (int right = left; right < LIMIT; right++) {
			int prod = left * right;
			if (is_palindrome(prod)) {
				max_pal = max(max_pal, prod);
			}
		}
	}
	cout << max_pal << endl;
	return 0;
}
