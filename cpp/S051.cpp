#include <iostream>
#include "primes.cpp"
#include "maths.cpp"

using namespace std;

static const int LIMIT = 1000 * 1000;
static const int TARGET = 8;

inline vector<vector<int>> get_digit_groups(int n) {
    vector<vector<int>> groups(10);
    int idx = 0;
    while (n > 0) {
        groups[n % 10].push_back(idx++);
        n /= 10;
    }
    return groups;
}

inline int replace(int n, int digit, const vector<int>& digit_group) {
    int reversed = 0, remaining = n;
    int digit_idx = 0, group_idx = 0;
    while (remaining > 0) {
        reversed *= 10;
        if (group_idx < digit_group.size() && digit_idx == digit_group[group_idx]) {
            reversed += digit;
            group_idx++;
        } else {
            reversed += remaining % 10;
        }
        digit_idx++;
        remaining /= 10;
    }
    return reverse_10(reversed);
}

inline bool is_eight_prime_fam(const vector<bool>& sieve, int n, const vector<int>& digit_group) {
    int count = 0;
    int n_digits = log_10(n) + 1;
    for (int digit = 0; digit < 10; digit++) {
        // do not replace most significant digit with 0
        if (digit_group.back() == n_digits - 1 && digit == 0) {
            continue;
        }
        count += sieve[replace(n, digit, digit_group)];
        if (digit - count >= (10 - TARGET)) {
            return false;
        }
    }
    return count == TARGET;
}

inline bool is_eight_prime_fam(vector<bool>& sieve, int n) {
    vector<vector<int>> groups = get_digit_groups(n);
    for (auto group : groups) {
        if (!group.empty() && is_eight_prime_fam(sieve, n, group)) {
            return true;
        }
    }
    return false;
}

int main() {
    vector<bool> sieve = compute_sieve(LIMIT);
    for (int i = 1; i < LIMIT; i++) {
        if (sieve[i] && is_eight_prime_fam(sieve, i)) {
            cout << i << endl;
            break;
        }
    }
    return 0;
}
