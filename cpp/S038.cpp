#include <iostream>

using namespace std;

static const int LIMIT = 10 * 1000;

inline int reverse(int n) {
    int reversed = 0;
    int remaining = n;
    while (remaining) {
        reversed = 10 * reversed + remaining % 10;
        remaining /= 10;
    }
    return reversed;
}

// is concat(k, 2k, ..., nk) pandigital?
inline int is_pandigital(int k, int n) {
    bool used_digits[9]; memset(used_digits, false, 9);
    int reversed_pan = 0, n_digits = 0;
    for (int mult = n; mult >= 1; mult--) {
        int prod = mult * k;
        while (prod) {
            int digit = prod % 10;
            if (!digit || used_digits[digit - 1]) {
                return -1;
            } else {
                used_digits[digit - 1] = true;
                n_digits++;
                reversed_pan = 10 * reversed_pan + digit;
                prod /= 10;
            }
        }
    }
    return n_digits == 9 ? reverse(reversed_pan) : -1;
}

int main() {
    int max_pan = -1;
    for (int i = 10; i < LIMIT; i++) {
        for (int n = 2; n < 5; n++) {
            max_pan = max(max_pan, is_pandigital(i, n));
        }
    }
    cout << max_pan << endl;
    return 0;
}