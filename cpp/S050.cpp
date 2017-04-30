#include <iostream>
#include "primes.cpp"

using namespace std;

static const int LIMIT = 1000 * 1000;

int main() {
    auto prime_res = compute_primes_under_with_sieve(LIMIT);
    vector<int> primes = prime_res.first;;
    vector<bool> sieve = prime_res.second;
    int base_chain_size = 0;
    int base_sum = 0;
    for (int i = 0; i < primes.size(); i++) {
        int prime = primes[i];
        if (base_sum + prime < LIMIT) {
            base_sum += prime;
            base_chain_size++;
        } else {
            break;
        }
    }
    for (int chain_size = base_chain_size; chain_size > 0; chain_size--) {
        int sum = base_sum;
        for (int to_remove = chain_size; to_remove < base_chain_size; to_remove++) {
            sum -= primes[to_remove];
        }
        int idx = 0;
        for (int i = 0; sum < LIMIT; i++) {
            if (sieve[sum]) {
                cout << sum << endl;
                return 0;
            }
            sum = sum - primes[i] + primes[i + chain_size];
        }
    }
    return 0;
}
