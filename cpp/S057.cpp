#include <iostream>
#include "biginteger.cpp"

using namespace std;

// given
static const int LIMIT = 1000;

// gcd(num, denom) == 1
inline void expand(BigInteger& num, BigInteger& denom) {
    num.add(denom);
    denom.add(num);
    num.swap(denom);
}

int main() {
    int count = 0;
    BigInteger num(3, 10), denom(2, 10);
    for (int i = 1; i < LIMIT; i++) {
        expand(num, denom);
        count += num.num_base_digits() > denom.num_base_digits();
    }
    cout << count << endl;
    return 0;
}
