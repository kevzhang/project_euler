#include <iostream>
#include "maths.cpp"

using namespace std;

// 9! * 7
static const int LIMIT = 2540160;

inline bool is_curious(int n) {
    int fac_sum = 0;
    int remaining = n;
    while (remaining > 0) {
        fac_sum += factorial(remaining % 10);
        remaining /= 10;
    }
    return fac_sum == n;
}

int main() {
    int sum = 0;
    for (int i = 10; i < LIMIT; i++) {
        sum += i * is_curious(i);
    }
    cout << sum << endl;
    return 0;
}