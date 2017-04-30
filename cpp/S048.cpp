#include <iostream>

using namespace std;

static const int LIMIT = 1000;
static const long MOD = 10L * 1000 * 1000 * 1000;

inline long n_to_n_mod(int n) {
    long res = n;
    for (int i = 1; i < n; i++) {
        res = (res * n) % MOD;
    }
    return res;
}

int main() {
    long sum = 0;
    for (int i = 1; i <= LIMIT; i++) {
        sum = (sum + n_to_n_mod(i)) % MOD;
    }
    cout << sum << endl;
    return 0;
}
