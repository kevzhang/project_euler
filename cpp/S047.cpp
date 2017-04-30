#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

static const int LIMIT = 1000 * 1000;
static const int TARGET = 4;

int main() {
    vector<short> factor_count_sieve(LIMIT);
    for (int i = 2; i <= sqrt(LIMIT); i++) {
        if (!factor_count_sieve[i]) {
            factor_count_sieve[i]++;
            for (int j = 2 * i; j < LIMIT; j += i) {
                factor_count_sieve[j]++;
            }
        }
    }
    int consec = 0;
    for (int i = 2; i < LIMIT; i++) {
        consec = factor_count_sieve[i] == TARGET ? consec + 1 : 0;
        if (consec == TARGET) {
            cout << i - TARGET + 1 << endl;
            break;
        }
    }
    return 0;
}
