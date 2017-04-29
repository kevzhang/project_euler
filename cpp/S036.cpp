#include <iostream>

using namespace std;

static const int LIMIT = 1000 * 1000;

inline bool is_palindrome(int n, int radix) {
    int reversed = 0;
    int remaining = n;
    while (remaining > 0) {
        reversed = reversed * radix + remaining % radix;
        remaining /= radix;
    }
    return reversed == n;
}

int main() {
    int sum = 0;
    for (int i = 1; i < LIMIT; i++) {
        sum += i * is_palindrome(i, 2) * is_palindrome(i, 10);
    }
    cout << sum << endl;
    return 0;
}
