#include <cmath>
#include <vector>

using namespace std;

vector<bool> compute_sieve(int limit) {
    vector<bool> sieve(limit);
    fill(sieve.begin(), sieve.end(), true);
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

inline bool is_prime_brute_force(int n) {
    int sqrt_n = sqrt(n);
    for (int i = 2; i <= sqrt_n;) {
        if (!(n % i)) {
            return false;
        }
        i += i == 2 ? 1 : 2;
    }
    return true;
}
