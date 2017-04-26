#include <iostream>

using namespace std;

static const long INPUT = 600851475143;

int main() {
	long cur_factor = 1;
	long remaining = INPUT;
	while (remaining > 1) {
		cur_factor += 2;
		while (remaining % cur_factor == 0) {
			remaining /= cur_factor;
		}
	}
	cout << cur_factor << endl;
	return 0;
}
