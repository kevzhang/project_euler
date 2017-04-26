#include <iostream>
#include "primes.cpp"

using namespace std;

static const int LIMIT = 2000 * 1000;

int main() {
	bool* sieve = compute_sieve(LIMIT);
	long sum = 0;
	for (int i = 0; i < LIMIT; i++) {
		// adds i iff i is prime as indicated by the sieve
		sum += sieve[i] * i;
	}
	cout << sum << endl;
	return 0;
}
