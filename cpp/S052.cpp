#include <iostream>
#include "primes.cpp"
#include "maths.cpp"

using namespace std;

static const int LIMIT = 6;

inline bool is_special(int n) {
    auto to_compare = digit_frequency_10(n);
    for (int i = 2; i <= LIMIT; i++) {
        if (to_compare != digit_frequency_10(i * n)) {
            return false;
        }
    }
    return true;
}

int main() {
    for (int i = 1;; i++) {
        if (is_special(i)) {
            cout << i << endl;
            break;
        }
    }
    return 0;
}
