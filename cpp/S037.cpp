#include <iostream>
#include "primes.cpp"
#include "maths.cpp"

using namespace std;

static const int LIMIT = 1000 * 1000;
// given
static const int TARGET = 11;

inline bool is_trunc_prime(vector<bool>& sieve, int prime) {
    int remaining = prime / 10;
    while (remaining > 0) {
        if (!sieve[remaining]) {
            return false;
        }
        remaining /= 10;
    }
    int pow_10 = POW_10[log_10(prime)];
    remaining = prime % pow_10;
    while (remaining > 0) {
        if (!sieve[remaining]) {
            return false;
        }
        pow_10 /= 10;
        remaining %= pow_10;
    }
    return true;
}

int main() {
    vector<bool> sieve = compute_sieve(LIMIT);
    int sum = 0, count = 0;
    for (int i = 10; i < LIMIT; i++) {
        if (sieve[i] && is_trunc_prime(sieve, i)) {
            sum += i;
            count++;
        }
        if (count == TARGET) {
            break;
        }
    }
    cout << sum << endl;
    return 0;
}