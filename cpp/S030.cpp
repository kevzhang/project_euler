#include <iostream>
#include "maths.cpp"

using namespace std;

static const int TARGET = 5;
// 9^5 * 7 = 413,343 < 1,000,000
static const int LIMIT = 1000 * 1000;

inline bool is_special(int n) {
    int fifth_sums = 0;
    int remaining = n;
    while (remaining > 0) {
        fifth_sums += int_pow(remaining % 10, 5);
        remaining /= 10;
    }
    return fifth_sums == n;
}

int main() {
    int sum = 0;
    for (int i = 10; i < LIMIT; i++) {
        sum += i * is_special(i);
    }
    cout << sum << endl;
    return 0;
}
