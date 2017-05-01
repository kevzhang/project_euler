#include <iostream>
#include "maths.cpp" 

using namespace std;

static const int LIMIT = 100;

inline bool greater_than_million(long n, long c) {
    return binomial(n, c) > 1000 * 1000;
}

int main() {
    int total = 0;
    for (int n = LIMIT; n > 0; n--) {
        int big_enough_c = -1;
        for (int c = 1; c <= n / 2; c++) {
            if (greater_than_million(n, c)) {
                big_enough_c = c;
                break;
            }
        }
        int cur_sum = big_enough_c < 0 ? 0 : (n / 2) - big_enough_c + 1;
        if (cur_sum) {
            total += 2 * cur_sum - !(n % 2);
        } else {
            break;
        }
    }
    cout << total << endl;
    return 0;
}
