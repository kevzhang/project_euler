#include <iostream>
#include "primes.cpp"
#include "maths.cpp"

using namespace std;

// greatest is 7-digit pandigital as sum[1, 8] and sum[1, 9] are divisible by 3
static const int LIMIT = 7;

// (1, 3, 2) => 231
inline int perm_to_int_reversed(const vector<int>& perm) {
    int cap = perm.size() + 1;
    int number = 0;
    for (int i = 0; i < perm.size(); i++) {
        number = 10 * number + (cap - perm[i]);
    }
    return number;
}

inline vector<int> get_perm(int n) {
    vector<int> perm;
    for (int i = 1; i <= n; i++) {
        perm.push_back(i);
    }
    return perm;
}

int main() {
    for (int n_digits = LIMIT; n_digits > 1; n_digits--) {
        auto perm = get_perm(n_digits);
        do {
            if (is_prime_brute_force(perm_to_int_reversed(perm))) {
                cout << perm_to_int_reversed(perm) << endl;
                return 0;
            }
        } while (increment_permutation(perm));
    }
    return 0;
}
