#include <iostream>
#include "maths.cpp"

using namespace std;

static const int INPUT = 100;

int main() {
	int ans = int_pow(triangle(INPUT), 2) - sum_of_squares(INPUT);
	cout << ans << endl;
	return 0;
}
