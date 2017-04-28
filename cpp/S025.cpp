#include <iostream>
#include <functional>
#include "maths.cpp"

using namespace std;

static const int TARGET = 1000;

BigInteger get_target() {
    BigInteger target = BigInteger::ONE();
    for (int i = 1; i < TARGET; i++) {
        target = target.times(10);
    }
    return target;
}

int main() {
    BigInteger target = get_target();
    BigInteger left = BigInteger::ONE();
    BigInteger right = BigInteger::ONE();
    for (int i = 3;; i++) {
        BigInteger next = left.add(right);
        if (next.compare(target) >= 0) {
            cout << i << endl;
            return 0;
        }
        left = right;
        right = next;
    }
}
