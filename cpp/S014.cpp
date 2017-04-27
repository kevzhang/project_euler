#include <iostream>

using namespace std;

static const int LIMIT = 1000 * 1000;

inline long next(long n) {
    return (n & 1) == 0 ? n / 2 : 3 * n + 1;
}

int get_length(long starting, int* table) {
    if (starting == 1) {
        return 1;
    } else if (starting >= LIMIT) {
        return 1 + get_length(next(starting), table);
    } else if (table[starting]) {
        return table[starting];
    } else {
        int length = 1 + get_length(next(starting), table);
        table[starting] = length;
        return length;
    }
}

int main() {
    int* table = (int*) calloc(sizeof(int), LIMIT);
    int max_len = -1, max_starting = -1;
    for (int i = 1; i < LIMIT; i++) {
        int cur_len = get_length(i, table);
        if (cur_len > max_len) {
            max_len = cur_len;
            max_starting = i;
        }
    }
    cout << max_starting << endl;
    return 0;
}
