#include <iostream>
#include "primes.cpp"
#include "maths.cpp"

using namespace std;

inline int primes_at_layer(const vector<int>& primes, int layer) {
    int n_primes = 0;
    for (int i = 0; i < 4; i++) {
        n_primes += is_prime_with_table(primes, int_pow(2 * layer + 1, 2) - i * (2 * layer));
    }
    return n_primes;
}

int main() {
    vector<int> primes = compute_primes_under(100 * 1000);
    int n_primes = 0;
    for (int i = 1;; i++) {
        n_primes += primes_at_layer(primes, i);
        if (10 * n_primes < 4 * i + 1) {
            cout << 2 * i + 1 << endl;
            return 0;
        }
    }
    return 0;
}
