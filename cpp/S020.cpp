#include <iostream>
#include "biginteger.cpp"
#include "strings.cpp"

using namespace std;

static const int LIMIT = 100;

int main() {
    BigInteger n = BigInteger::ONE();
    for (int i = 2; i <= LIMIT; i++) {
        n.times(i);
    }
    cout << sum_digits(n.to_string()) << endl;
    return 0;
}
