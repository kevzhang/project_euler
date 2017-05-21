#include <iostream>
#include <map>
#include "maths.cpp"

using namespace std;

static const int TARGET = 5;

inline long get_signature(long n) {
    auto freq = digit_frequency_10(n);
    long res = 0;
    for (int i = 9; i >= 0; i--) {
        while (freq[i]) {
            res = 10 * res + i;
            freq[i]--;
        }
    }
    return res;
}

int main() {
    multimap<long, long> hits;
    for (long n = 1;; n++) {
        long cubed = int_pow(n, 3L);
        long signature = get_signature(cubed);
        hits.insert({signature, cubed});
        // maybe being greedy here is cheating
        if (hits.count(signature) == TARGET) {
            cout << hits.equal_range(signature).first->second << endl;
            return 0;
        }
    }
    return 0;
}
