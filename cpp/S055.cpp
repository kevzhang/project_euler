#include <iostream>
#include "biginteger.cpp"
#include "strings.cpp"

using namespace std;

// given
static const int LIMIT = 10 * 1000;
// given
static const int MAX_ITERS = 49;

// TODO: optimize BigInteger
inline bool is_lychrel(int n) {
    BigInteger big(n);
    for (int i = 0; i < MAX_ITERS; i++) {
        string num = big.to_string();
        reverse(num.begin(), num.end());
        BigInteger reversed(num);
        big.add(reversed);
        if (is_palindrome(big.to_string())) {
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
