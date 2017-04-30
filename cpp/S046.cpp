#include <iostream>
#include "primes.cpp"

using namespace std;

static int LIMIT = 10 * 1000;

int main() {
    vector<bool> sieve = compute_sieve(LIMIT);
    for (int composite = 3; composite < LIMIT; composite += 2) {
        if (sieve[composite]) {
            continue;
        }
        for (int sq = 1; 2 * sq * sq < composite; sq++) {
            if (sieve[composite - 2 * sq * sq]) {
                goto continue_search;
            }
        }
        cout << composite << endl;
        return 0;
        continue_search:;
    }
    return 0;
}
