#include <iostream>
#include <cmath>
#include "maths.cpp"

using namespace std;

static const int LIMIT = 1000;

// same as S009.cpp
bool has_solution(int side_sum, int c) {
	int sum_of_squares = c * c;
	int sum_of_ab = side_sum - c;
	pair<double, double> solns =
		solve_quadratic(2, -2 * sum_of_ab, sum_of_ab * sum_of_ab - sum_of_squares);
	if (isnan(solns.first) || isnan(solns.second)) {
        return false;
	}
    return int_pow((int)solns.first, 2) + int_pow((int)solns.second, 2) == sum_of_squares;
}

int main() {
    int max_solutions = -1, max_p = -1;
    for (int p = 1; p < LIMIT; p++) {
        int min_c = p * sqrt(2) / (sqrt(2) + 2);
        int max_c = p / 2;
        int n_solutions = 0;
        for (int c = min_c; c < max_c; c++) {
            n_solutions += has_solution(p, c);
        }
        if (n_solutions > max_solutions) {
            max_solutions = n_solutions;
            max_p = p;
        }
    }
    cout << max_p << endl;
    return 0;
}
