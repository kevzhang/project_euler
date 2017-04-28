#include <iostream>
#include "maths.cpp"

using namespace std;

static const int LIMIT = 10 * 1000;

int proper_divisor_sum(int n) {
    return sum_divisors_brute_force(n) - n;
}

bool is_amicable_sum(int n) {
    int pds = proper_divisor_sum(n);
    if (pds == n) {
        return false;
    } else {
        return proper_divisor_sum(pds) == n;
    }
}

int main() {
    int sum = 0;
    for (int i = 2; i < LIMIT; i++) {
        sum += i * is_amicable_sum(i);
    }
    cout << sum << endl;
    return 0;
}
