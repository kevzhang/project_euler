#include <iostream>
#include "biginteger.cpp"
#include "strings.cpp"

using namespace std;

// given
static const int LIMIT = 10 * 1000;
// given
static const int MAX_ITERS = 49;

inline bool is_lychrel(int n) {
    BigInteger big(n, 10);
    for (int i = 0; i < MAX_ITERS; i++) {
        BigInteger copy = big;
        big.reverse_base();
        big.add(copy);
        if(big.is_base_palindrome()) {
            return false;
        }
    }
    return true;
}

int main() {
    int n_lychrels = 0;
    for (int i = 1; i < LIMIT; i++) {
        n_lychrels += is_lychrel(i);
    }
    cout << n_lychrels << endl;
    return 0;
}
