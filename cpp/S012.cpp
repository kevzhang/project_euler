#include <iostream>
#include "maths.cpp"

using namespace std;

static const int TARGET = 500;

int main() {
	int idx = 1;
	while (true) {
		int tri = triangle(idx++);
		if (num_divisors(tri) > TARGET) {
			cout << tri << endl;
			return 0;
		}
	}
}
