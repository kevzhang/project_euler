#include <iostream>
#include <cmath>
#include "maths.cpp"

using namespace std;

static const int SUM = 1000;

pair<int, int> find_ab(int side_sum, int c) {
	int sum_of_squares = c * c;
	int sum_of_ab = side_sum - c;
	pair<double, double> solns =
		solve_quadratic(2, -2 * sum_of_ab, sum_of_ab * sum_of_ab - sum_of_squares);
	if (isnan(solns.first) || isnan(solns.second)) {
		return make_pair(-1, -1);
	} else {
		if (int_pow(solns.first, 2) + int_pow(solns.second, 2) == sum_of_squares) {
			return make_pair(solns.first, solns.second);
		} else {
			return make_pair(-1, -1);
		}
	}
}

int main() {
	int min_c = SUM * sqrt(2) / (sqrt(2) + 2);
	int max_c = SUM / 2;
	for (int c = min_c; c < max_c; c++) {
		pair<int, int> ab = find_ab(SUM, c);
		if (ab.first != -1) {
			cout << ab.first * ab.second * c << endl;
			break;
		}
	}
	return 0;
}
