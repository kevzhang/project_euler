#include <iostream>
#include <vector>
#include "maths.cpp"

using namespace std;

inline bool frac_eql(int topA, int botA, int topB, int botB) {
    return botB * topA == botA * topB;
}

int main() {
    vector<pair<int, int>> fracs;
    for (int top = 10; top < 100; top++ ) {
        for (int bot = top + 1; bot < 100; bot++) {
            if (top % 10 == 0 || bot % 10 == 0) {
                continue;
            }
            // cancellations must happen diagonally
            int topL = top / 10, topR = top % 10;
            int botL = bot / 10, botR = bot % 10;
            if (topL == botR && frac_eql(top, bot, topR, botL)) {
                fracs.push_back({top, bot});
            } else if (topR == botL && frac_eql(top, bot, topL, botR)) {
                fracs.push_back({top, bot});
            }
        }
    }
    int num_prod = 1, denom_prod = 1;
    for (auto f : fracs) {
        num_prod *= f.first;
        denom_prod *= f.second;
    }
    cout << denom_prod / gcd(num_prod, denom_prod) << endl;
    return 0;
}