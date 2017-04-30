#include <iostream>
#include <climits>
#include "maths.cpp"

using namespace std;

static const int LIMIT = 100 * 1000;

inline bool is_pentagonal(int n) {
    int maybe_idx = (1 + sqrt(1 + 24 * n)) / 6;
    return pentagonal(maybe_idx) == n;
}

int main() {
    int pentagonals[LIMIT];
    for (int i = 1; i < LIMIT; i++) {
        pentagonals[i] = pentagonal(i);
    }
    int D = INT_MAX;
    for (int k = 2; k < LIMIT; k++) {
        if (pentagonals[k] > D) {
            break;
        }
        for (int j = 1; j < k; j++) {
            int pj = pentagonal(j), pk = pentagonal(k);
            if (is_pentagonal(pk - pj) && is_pentagonal(pk + pj)) {
                D = min(D, pk - pj);
            }
        }
    }
    cout << D << endl;
    return 0;
}
