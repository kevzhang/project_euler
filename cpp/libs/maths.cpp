#include <cmath>

int gcd(int a, int b) {
    return a == 0 ? b : gcd(b % a, a);
}

int triangle(int n) {
    return n * (n + 1) / 2;
}

int int_pow(int a, int b) {
    if (b == 0) {
        return 1;
    }
    int pow = a;
    for (int i = 1; i < b; i++) {
        pow *= a;
    }
    return pow;
}

int max_int(int size, int arr[]) {
    int max_int = arr[0];
    for (int i = 1; i < size; i++) {
        max_int = std::max(max_int, arr[i]);
    }
    return max_int;
}

std::pair<double, double> solve_quadratic(int a, int b, int c) {
    double first = (-b + sqrt(b * b - 4 * a * c)) / (2 * a);
    double second = (-b - sqrt(b * b - 4 * a * c)) / (2 * a);
    return std::make_pair(first, second);
}

int sum_of_squares(int n) {
    return n * (n + 1) * (2 * n + 1) / 6;
}
