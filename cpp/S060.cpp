#include <iostream>
#include "primes.cpp"
#include "maths.cpp"

using namespace std;

static const int TARGET = 5;
static const int LIMIT = 10 * 1000;

inline int concat(int a, int b) {
    return a * POW_10[log_10(b) + 1] + b;
}

inline bool concats_prime(vector<uint8_t>& prime_concat_table, const vector<int>& primes,
        const int left, const int right) {
    int cached = prime_concat_table[left * LIMIT + right];
    if (cached) {
        return cached == 1;
    } else {
        int concat_val = concat(left, right);
        bool concats_to_prime = is_prime_with_table(primes, concat_val);
        prime_concat_table[left * LIMIT + right] = concats_to_prime ? 1 : -1;
        return concats_to_prime;
   }
}

inline bool is_compatible(vector<uint8_t>& prime_concat_table, const vector<int>& primes,
        const vector<int>& search, int layer, int proposed_prime) {
    for (int i = layer - 1; i >= 0; i--) {
        int prime = search[i];
        if (!concats_prime(prime_concat_table, primes, prime, proposed_prime)
            || !concats_prime(prime_concat_table, primes, proposed_prime, prime)) {
            return false;
        }
    }
    return true;
}

int search_helper(vector<uint8_t>& prime_concat_table, const vector<int>& primes,
        vector<int>& search, int layer, int starting_at) {
    if (layer == TARGET) {
        return sum(search);
    }
    for (int search_idx = starting_at; search_idx < primes.size(); search_idx++) {
        if (is_compatible(prime_concat_table, primes, search, layer, primes[search_idx])) {
            search[layer] = primes[search_idx];
            int sub_call = search_helper(prime_concat_table, primes, search, layer + 1, search_idx + 1);
            if (sub_call) {
                return sub_call;
            }
        }
    }
    return 0;
}

int main() {
    vector<int> primes = compute_primes_under(LIMIT);
    vector<uint8_t> prime_concat_table(LIMIT * LIMIT);
    vector<int> search(TARGET);
    cout << search_helper(prime_concat_table, primes, search, 0, 0) << endl;
    return 0;
}
