#include <iostream>
#include "primes.cpp"

using namespace std;

static const int LIMIT = 1000 * 1000;
static const int TARGET = 10001;

int main() {
	bool* sieve = compute_sieve(LIMIT);
	int pIdx = 0;
	for (int i = 0; i < LIMIT; i++) {
		if (sieve[i]) {
			pIdx++;
		}
		if (pIdx == TARGET) {
			cout << i << endl;
			break;
		}
	}
	// free(sieve);
	return 0;
}
