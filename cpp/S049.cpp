#include <iostream>
#include "primes.cpp"
#include "maths.cpp"

using namespace std;

static const int LIMIT = 10 * 1000;

// is a, b, c permutations of each other
inline bool is_perms(int a, int b, int c) {
    vector<uint8_t> a_freq = digit_frequency_10(a);
    vector<uint8_t> b_freq = digit_frequency_10(b);
    return a_freq == b_freq && a_freq == digit_frequency_10(c);
}

int main() {
    vector<bool> sieve = compute_sieve(LIMIT);
    for (int i = 1000; i < LIMIT; i++) {
        if (!sieve[i]) {
            continue;
        }
        int inc_limit = (LIMIT - 1) / 2;
        for (int inc = 1; inc <= inc_limit; inc++) {
            int a = i, b = i + inc, c = i + 2 * inc;
            // given in problem statement
            if (a == 1487 && inc == 3330) {
                continue;
            }
            if (sieve[b] && sieve[c] && is_perms(a, b, c)) {
                cout << to_string(a) + to_string(b) + to_string(c) << endl;
                return 0;
            }
        }
    }
    return 0;
}
