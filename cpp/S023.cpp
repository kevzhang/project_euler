#include <iostream>
#include <vector>
#include <algorithm>
#include "maths.cpp"

using namespace std;

// given
static const int LIMIT = 28123;

inline bool is_abundant(int n) {
    return sum_divisors_brute_force(n) - n > n;
}

int main() {
    vector<int> abundants;
    for (int i = 2; i < LIMIT; i++) {
        if (is_abundant(i)) {
            abundants.push_back(i);
        }
    }
    vector<bool> can_sum_to(LIMIT);
    for (int i = 0; i < abundants.size(); i++) {
        for (int j = i; j < abundants.size(); j++) {
            can_sum_to[abundants[i] + abundants[j]] = true;
        }
    }
    int bad_sum = 0;
    for (int i = 0; i < LIMIT; i++) {
        bad_sum += i * !can_sum_to[i];
    }
    cout << bad_sum << endl;
    return 0;
}
