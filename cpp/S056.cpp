#include <iostream>
#include "biginteger.cpp"
#include "strings.cpp"

using namespace std;

// given
static const int LIMIT = 100;

int main() {
    int max_digit_sum = -1;
    for (int a = 1; a < LIMIT; a++) {
        // use base 10 for faster digit sums
        BigInteger big(a, 10);
        max_digit_sum = max(max_digit_sum, (int) big.sum_base());
        for (int b = 1; b < LIMIT; b++) {
            big.times(a);
            max_digit_sum = max(max_digit_sum, (int) big.sum_base());
        }
    }
    cout << max_digit_sum << endl;
    return 0;
}
