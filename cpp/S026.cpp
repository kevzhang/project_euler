#include <iostream>
#include <set>

using namespace std;

static const int LIMIT = 1000;

int recurring_length(int d) {
    set<int> remainders;
    int remain = 1;
    while (true) {
        while (remain < d) {
            remain *= 10;
        }
        remain %= d;
        if (!remain) {
            return 0;
        } else if (remainders.count(remain)) {
            return remainders.size();
        } else {
            remainders.insert(remain);
        }
    }
}

int main() {
    int max_d = -1, max_len = -1;
    for (int i = 1; i < LIMIT; i++) {
        int cur_len = recurring_length(i);
        if (cur_len > max_len) {
            max_len = cur_len;
            max_d = i;
        }
    }
    cout << max_d << endl;
    return 0;
}
