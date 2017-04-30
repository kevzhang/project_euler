#include <iostream>
#include "maths.cpp"

using namespace std;

static const int FACTORS[] = {2, 3, 5, 7, 11, 13, 17};

inline bool is_special(long n) {
    for (int i = 0; i < 7; i++) {
        if ((n % 1000) % FACTORS[6 - i]) {
            return false;
        }
        n /= 10;
    }
    return true;
}

// middle_digit is either 0 or 5
inline long to_num(const vector<int>& perm, int middle_digit) {
    long num = 0;
    for (int i = 0; i < 5; i++) {
        num = num * 10 + perm[i];
    }
    num = num * 10 + middle_digit;
    for (int i = 5; i < 9; i++) {
        num = num * 10 + perm[i];
    }
    return num;
}

int main() {
    // exclude 5
    vector<int> perm1 = {1, 0, 2, 3, 4, 6, 7, 8, 9};
    // exclude 0
    vector<int> perm2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    long sum = 0;
    do {
        long num = to_num(perm1, 5);
        sum += num * is_special(num);
    } while (increment_permutation(perm1));
    do {
        long num = to_num(perm2, 0);
        sum += num * is_special(num);
    } while (increment_permutation(perm2));
    cout << sum << endl;
    return 0;
}
