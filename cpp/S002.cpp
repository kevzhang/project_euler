#include <iostream>

using namespace std;

static const int LIMIT = 4 * 1000 * 1000;

int main() {
	int left = 1, right = 2;
	long sum = 0;
	while (right < LIMIT) {
		if (right % 2 == 0) {
			sum += right;
		}
		int next = left + right;
		left = right;
		right = next;
	}
	cout << sum << endl;
	return 0;
}
