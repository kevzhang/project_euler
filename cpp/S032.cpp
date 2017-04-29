#include <iostream>
#include <set>

using namespace std;

inline bool is_1_9_pandigital(int a, int b, int n) {
    bool used[9];
    memset(used, false, 9);
    int nums[] = {a, b, n};
    int digits = 0;
    for (int num : nums) {
        while (num > 0) {
            int digit = num % 10;
            if (!digit || used[digit - 1]) {
                return false;
            }
            used[digit - 1] = true;
            digits++;
            num /= 10;
        }
    }
    return digits == 9;
}

int main() {
    set<int> products;
    // a has 1 digit and b has 4 digits
    for (int a = 1; a < 10; a++) {
        for (int b = 1000; b < 10000; b++) {
            if (is_1_9_pandigital(a, b, a * b)) {
                products.insert(a * b);
            }
        }
    }
    // a has 2 digits and b has 3 digits
    for (int a = 10; a < 100; a++) {
        for (int b = 100; b < 1000; b++) {
            if (is_1_9_pandigital(a, b, a * b)) {
                products.insert(a * b);
            }
        }
    }
    int sum = 0;
    for (int prod : products) {
        sum += prod;
    }
    cout << sum << endl;
    return 0;
}
