#include <iostream>
#include "primes.cpp"

using namespace std;

static const int LIMIT = 10 * 1000;

inline vector<char> digit_frequency(int n) {
    vector<char> digit_freq(10);
    while (n > 0) {
        digit_freq[n % 10]++;
        n /= 10;
    }
    return digit_freq;
}

// is a, b, c permutations of each other
inline bool is_perms(int a, int b, int c) {
    vector<char> a_freq = digit_frequency(a);
    vector<char> b_freq = digit_frequency(b);
    return a_freq == b_freq && a_freq == digit_frequency(c);
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
