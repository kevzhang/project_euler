#include <vector>
#include <iostream>
#include "maths.cpp"

using namespace std;

inline int fun(int id, int n) {
    switch (id) {
        case 0: return triangle(n);
        case 1: return n * n;
        case 2: return pentagonal(n);
        case 3: return hexagonal(n);
        case 4: return heptagonal(n);
        case 5: return octagonal(n);
    }
    return -1;
}

vector<vector<bool>> generate_tables() {
    vector<vector<bool>> table(6);
    for (int i = 0; i < 6; i++) {
        table[i] = vector<bool>(10000);
        int n = 1;
        int val = fun(i, n);
        do {
            table[i][val] = true;
            val = fun(i, ++n);
        } while (val < 10000);
    }
    return table;
}

inline vector<int> get_unused(vector<bool> used) {
    vector<int> unused;
    for (int i = 0; i < 6; i++) {
        if (!used[i]) {
            unused.push_back(i);
        }
    }
    return unused;
}

int search(const vector<vector<bool>>& table, vector<int>& prev, vector<bool> used) {
    vector<int> unused = get_unused(used);
    int res = 0;
    if (unused.size() == 1) {
        int forced_num = 100 * (prev.back() % 100) + prev.front() / 100;
        return table[unused.front()][forced_num] * (forced_num + sum(prev));
    } else if (unused.size() == 6) {
        used[5] = true;
        for (int first_num = 1000; first_num < 10000; first_num++) {
            if (table[5][first_num]) {
                prev.push_back(first_num);
                res = max(res, search(table, prev, used));
                prev.pop_back();
            }
        }
        return res;
    }
    // 2 <= |unused| <= 5
    int prev_base = 100 * (prev.back() % 100);
    for (int unused_idx : unused) {
        used[unused_idx] = true;
        for (int tail = 10; tail < 100; tail++) {
            int num = prev_base + tail;
            if (table[unused_idx][num]) {
                prev.push_back(num);
                res = max(res, search(table, prev, used));
                prev.pop_back();
            }
        }
        used[unused_idx] = false;
    }
    return res;
}

int main() {
    vector<vector<bool>> tables = generate_tables();
    vector<bool> used(6);
    vector<int> prev;
    cout << search(tables, prev, used) << endl;
    return 0;
}
