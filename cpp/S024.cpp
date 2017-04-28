#include <iostream>
#include <vector>
#include <algorithm>
#include "maths.cpp"
#include "strings.cpp"

using namespace std;

static const int TARGET = 1000 * 1000;

int nth_unused(vector<bool>& used, int n) {
    int idx = 0;
    for (int i = 0; i < used.size(); i++) {
        if (!used[i]) {
            if (idx == n) {
                return i;
            }
            idx++;
        }
    }
    return -1;
}

vector<int> nth_permutation(int size, int idx) {
    vector<bool> used(size);
    vector<int> perm;
    int remaining = idx;
    for (int i = 0; i < size; i++) {
        int inner_blk_size = factorial(size - i - 1);
        int outer_idx = remaining / inner_blk_size;
        int unused = nth_unused(used, outer_idx);
        used[unused] = true;
        perm.push_back(unused);
        remaining -= inner_blk_size * outer_idx;
    }
    return perm;
}

int main() {
    cout << join("", nth_permutation(10, TARGET - 1)) << endl;
    return 0;
}
