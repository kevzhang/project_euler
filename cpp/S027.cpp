#include <iostream>
#include "primes.cpp"

using namespace std;

inline int quadratic(int a, int b, int n) {
    return n * n + a * n + b;
}

inline int n_consec_prime(int a, int b, vector<bool>& sieve) {
    for (int i = 0;;i++) {
        int res = quadratic(a, b, i);
        if (res <= 0 || !sieve[res]) {
            return i;
        }
    }
}

int main() {
    vector<bool> sieve = compute_sieve(100 * 1000);
    int ab_prod = -1;
    int max_len = -1;
    for (int a = -999; a <= 999; a++) {
        for (int b = 0; b <= 1000; b++) {
            if (!sieve[b]) {
                continue;
            }
            int cur_len = n_consec_prime(a, b, sieve);
            if (cur_len > max_len) {
                max_len = cur_len;
                ab_prod = a * b;
            }
        }
    }
    cout << ab_prod << endl;
    return 0;
}
