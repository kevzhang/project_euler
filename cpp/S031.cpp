#include <iostream>
#include <vector>

using namespace std;

static const vector<int> DENOMS = {1, 2, 5, 10, 20, 50, 100, 200};
static const int TARGET = 200;

int main() {
    // bottom-up DP using rolling tables
    // val @ row[i] = # ways to add to i for current number of coins
    vector<int> row(TARGET + 1);
    // there's 1 way to sum to 0
    row[0] = 1;
    for (int i = 0; i < DENOMS.size(); i++) {
        int cur_denom = DENOMS[i];
        // only consider totals greater than the current denom
        for (int t = cur_denom; t <= TARGET; t++) {
            row[t] += row[t - cur_denom];
        }
    }
    cout << row[TARGET] << endl;
    return 0;
}
