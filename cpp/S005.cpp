#include <iostream>
#include "maths.cpp"

using namespace std;

static const int LIMIT = 20;

int main() {
	int prod = 1;
	for (int i = 2; i <= LIMIT; i++) {
		int deficient = i / gcd(prod, i);
		prod *= deficient;
	}
	cout << prod << endl;
	return 0;
}
