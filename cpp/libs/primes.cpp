#include <cmath>

bool* compute_sieve(int limit) {
    bool* sieve = (bool*) malloc(sizeof(bool) * limit);
    memset(sieve, 1, sizeof(bool) * limit);
    sieve[0] = false;
    sieve[1] = false;
    int sqrt_limit = (int) sqrt(limit);
    for (int i = 2; i <= sqrt_limit; i++) {
        if (sieve[i]) {
            for (int j = 2 * i; j < limit; j += i) {
                sieve[j] = false;
            }
        }
    }
    return sieve;
}
