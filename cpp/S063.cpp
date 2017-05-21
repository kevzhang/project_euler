#include <iostream>
#include "maths.cpp"

using namespace std;

static const unsigned long BASE_LIMIT = 9; // this is clear
static const unsigned long POW_LIMIT = 21; // 9 ^ 21 is the cap

int main() {
    int count = 0;
    for (unsigned long b = 1; b <= BASE_LIMIT; b++) {
        // 9 ^ 21 overflows, so only go up to 9 ^ 21
        for (unsigned long p = 1; p <= POW_LIMIT - 1; p++) {
            unsigned long res = int_pow(b, p);
            count += (log_10(res) + 1) == p;
        }
    }
    // + 1 to account for 9 ^ 21, which meets the conditions
    cout << count + 1 << endl;
    return 0;
}
