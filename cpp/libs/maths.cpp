#include <cmath>
#include <vector>
#include <climits>

using namespace std;

int gcd(int a, int b) {
    return a == 0 ? b : gcd(b % a, a);
}

int triangle(int n) {
    return n * (n + 1) / 2;
}

int int_pow(int a, int b) {
    if (b == 0) {
        return 1;
    }
    int pow = a;
    for (int i = 1; i < b; i++) {
        pow *= a;
    }
    return pow;
}

int max_int(int size, int arr[]) {
    int max_int = arr[0];
    for (int i = 1; i < size; i++) {
        max_int = max(max_int, arr[i]);
    }
    return max_int;
}

int num_divisors(int n) {
    int n_divisors = 1;
    int remaining = n;
    int cur_factor = 2;
    while (remaining > 1) {
        int factor_count = 0;
        while (remaining % cur_factor == 0) {
            remaining /= cur_factor;
            factor_count++;
        }
        n_divisors *= (factor_count + 1);
        cur_factor += cur_factor == 2 ? 1 : 2;
    }
    return n_divisors;
}

pair<double, double> solve_quadratic(int a, int b, int c) {
    double first = (-b + sqrt(b * b - 4 * a * c)) / (2 * a);
    double second = (-b - sqrt(b * b - 4 * a * c)) / (2 * a);
    return make_pair(first, second);
}

int sum_of_squares(int n) {
    return n * (n + 1) * (2 * n + 1) / 6;
}

inline unsigned int highest_order(unsigned int n) {
    unsigned int mask = 1;
    while (n >>= 1) {
        mask <<= 1;
    }
    return mask;
}

class BigInteger {
    static const unsigned long BASE = 1L << 32;
    private:
        // little endian, in increasing order of bits
        vector<unsigned int> data;
        void _add(vector<unsigned int>& number, const vector<unsigned int>& by, unsigned long base) {
            unsigned int carry = 0;
            int common_length = min(number.size(), by.size());
            // add common bits
            for (int i = 0; i < common_length; i++) {
                unsigned long result = ((unsigned long)number[i]) + by[i] + carry;
                number[i] = (unsigned int) (result % base);
                carry = result / base;
            }
            // my number is bigger
            for (int i = common_length; carry && i < number.size(); i++) {
                unsigned long result = ((unsigned long)number[i]) + carry;
                number[i] = (unsigned int) (result % base);
                carry = result / base;
            }
            // their number is bigger
            for (int i = common_length; i < by.size(); i++) {
                unsigned long result = ((unsigned long)by[i]) + carry;
                number.push_back((unsigned int) (result % base));
                carry = result / base;
            }
            // finish adding carry amounts
            while (carry) {
                number.push_back(carry % base);
                carry /= base;
            }
        }
        void _add(vector<unsigned int>& number, unsigned int by, unsigned long base) {
            unsigned int carry = by;
            for (int i = 0; i < number.size(); i++) {
                unsigned long result = ((unsigned long)number[i]) + carry;
                number[i] = (unsigned int) (result % base);
                carry = result / base;
            }
            while (carry) {
                number.push_back(carry % base);
                carry /= base;
            }
        }
        void _times(vector<unsigned int>& number, unsigned int by, unsigned long base) {
            unsigned int carry = 0;
            for (int i = 0; i < number.size(); i++) {
                unsigned long result = ((unsigned long)number[i]) * by + carry;
                number[i] = (unsigned int) (result % base);
                carry = result / base;
            }
            while (carry) {
                number.push_back(carry % base);
                carry /= base;
            }
        }
        static vector<unsigned int> zero() {
            vector<unsigned int> number;
            number.push_back(0);
            return number;
        }
        static vector<unsigned int> one() {
            vector<unsigned int> number;
            number.push_back(1);
            return number;
        }
    public:
        static BigInteger ZERO() {
            return BigInteger(zero());
        }
        static BigInteger ONE() {
            return BigInteger(one());
        }
        BigInteger(const BigInteger& big) {
            data = big.data;
        }
        BigInteger(const vector<unsigned int>& vec) {
            data = vec;
        }
        BigInteger(const string& number) {
            data = zero();
            for (int i = 0; i < number.length(); i++) {
                _times(data, 10, BASE);
                _add(data, number[i] - '0', BASE);
            }
        }
        BigInteger add(const BigInteger& big) {
            BigInteger copy = *this;
            copy._add(copy.data, big.data, BASE);
            return copy;
        }
        string to_string() {
            string str;
            vector<unsigned int> base_10 = zero();
            unsigned int highest_block = data.back();
            unsigned int initial_mask = highest_order(highest_block);
            while (initial_mask > 0) {
                unsigned int to_add = initial_mask & highest_block;
                _times(base_10, 2, 10);
                if (to_add) {
                    _add(base_10, 1, 10);
                }
                initial_mask >>= 1;
            }
            for (int idx = data.size() - 2; idx >= 0; idx--) {
                unsigned int mask = 1 << 31;
                while (mask > 0) {
                    unsigned int to_add = mask & data[idx];
                    _times(base_10, 2, 10);
                    if (to_add) {
                        _add(base_10, 1, 10);
                    }
                    mask >>= 1;
                }
            }
            for (int idx = base_10.size() - 1; idx >= 0; idx--) {
                str += std::to_string(base_10[idx]);
            }
            return str;
        }
        vector<unsigned int>& get_data() {
            return data;
        }
};
