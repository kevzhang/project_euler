#include <iostream>
#include "primes.cpp"
#include "maths.cpp"

using namespace std;

static const int LIMIT = 1000 * 1000;

inline bool is_circular_prime(vector<bool>& sieve, int prime) {
    int rotation = prime;
    int pow_10 = POW_10[log_10(prime)];
    do {
        int digit = rotation % 10;
        if (!digit) {
            return false;
        }
        rotation  = (rotation / 10) + pow_10 * digit;
        if (!sieve[rotation]) {
            return false;
        }
    } while (rotation != prime);
    return true;
}

int main() {
    vector<bool> sieve = compute_sieve(LIMIT);
    int n_circular = 0;
    for (int i = 2; i < LIMIT; i++) {
        if (sieve[i]) {
            n_circular += is_circular_prime(sieve, i);
        }
    }
    cout << n_circular << endl;
    return 0;
}