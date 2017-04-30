#include <cmath>
#include <vector>
#include <algorithm>
#include <map>
#include <climits>

using namespace std;

// idea taken from the Guava LongMath Library
static const long _FACTORIALS[] = {
    1L,
    1L,
    1L * 2,
    1L * 2 * 3,
    1L * 2 * 3 * 4,
    1L * 2 * 3 * 4 * 5,
    1L * 2 * 3 * 4 * 5 * 6,
    1L * 2 * 3 * 4 * 5 * 6 * 7,
    1L * 2 * 3 * 4 * 5 * 6 * 7 * 8,
    1L * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9,
    1L * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10,
    1L * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10 * 11,
    1L * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10 * 11 * 12,
    1L * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10 * 11 * 12 * 13,
    1L * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10 * 11 * 12 * 13 * 14,
    1L * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10 * 11 * 12 * 13 * 14 * 15,
    1L * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10 * 11 * 12 * 13 * 14 * 15 * 16,
    1L * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10 * 11 * 12 * 13 * 14 * 15 * 16 * 17,
    1L * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10 * 11 * 12 * 13 * 14 * 15 * 16 * 17 * 18,
    1L * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10 * 11 * 12 * 13 * 14 * 15 * 16 * 17 * 18 * 19,
    1L * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10 * 11 * 12 * 13 * 14 * 15 * 16 * 17 * 18 * 19 * 20
};

inline long factorial(int n) {
    if (n >= 0 && n <= 20) {
        return _FACTORIALS[n];
    } else {
        return -1;
    }
}

static const unsigned long POW_10[] = {
    1L,
    10L,
    100L,
    1000L,
    10000L,
    100000L,
    1000000L,
    10000000L,
    100000000L,
    1000000000L,
    10000000000L,
    100000000000L,
    1000000000000L,
    10000000000000L,
    100000000000000L,
    1000000000000000L,
    10000000000000000L,
    100000000000000000L,
    1000000000000000000L,
    10000000000000000000UL,
};

template<typename T> int log_10(T n) {
    for (int i = 0; i < 20; i++) {
        if (POW_10[i] > n) {
            return i - 1;
        }
    }
    return 19;
}

template<typename T> T gcd(T a, T b) {
    return a == 0 ? b : gcd(b % a, a);
}

inline long binomial(long n, long k) {
    long res = 1;
    k = min(k, n - k);
    long to_divide = factorial(k);
    for (int a = n; a > (n - k); a--) {
        long can_divide = gcd((long) a, to_divide);
        res *= a / can_divide;
        to_divide /= can_divide;
    }
    res /= to_divide;
    return res;
}

template<typename T> T triangle(T n) {
    return n * (n + 1) / 2;
}

template<typename T> T pentagonal(T n) {
    return n * (3 * n - 1) / 2;
}

template<typename T> T hexagonal(T n) {
    return n * (2 * n - 1);
}

template<typename T> T int_pow(T a, T b) {
    if (b == 0) {
        return 1;
    }
    T pow = a;
    for (T i = 1; i < b; i++) {
        pow *= a;
    }
    return pow;
}

template<typename T> T max_val(int size, T arr[]) {
    T max_val = arr[0];
    for (int i = 1; i < size; i++) {
        max_val = max(max_val, arr[i]);
    }
    return max_val;
}

// @ vec contains distinct values
template<typename T> bool increment_permutation(vector<T>& vec) {
    int pivot_idx = -1;
    for (int i = vec.size() - 2; i >= 0; i--) {
        if (vec[i] < vec[i + 1]) {
            pivot_idx = i;
            break;
        }
    }
    if (pivot_idx < 0) {
        return false;
    }
    T min_greater_than_pivot_idx = pivot_idx + 1;
    for (int i = vec.size() - 1; i > pivot_idx + 1; i--) {
        if (vec[i] > vec[pivot_idx] && vec[i] < vec[min_greater_than_pivot_idx]) {
            min_greater_than_pivot_idx = i;
        }
    }
    swap(vec[pivot_idx], vec[min_greater_than_pivot_idx]);
    sort(vec.begin() + pivot_idx + 1, vec.end());
    return true;
}

template<typename T> bool increment_vector(vector<T>& vec, vector<T>& limits) {
    for (int i = vec.size() - 1; i >= 0; i--) {
        if (vec[i] < limits[i] - 1) {
            vec[i]++;
            for (int j = i + 1; j < vec.size(); j++) {
                vec[j] = 0;
            }
            return true;
        }
    }
    return false;
}

template<typename K, typename V> vector<pair<K, V>> get_entries(map<K, V> m) {
    vector<pair<K, V>> entries(m.size());
    for (auto elem : m) {
        entries.push_back({elem.first, elem.second});
    }
    return entries;
}

template<typename T> map<T, int> prime_factorization(T n) {
    map<T, int> factorization;
    T remaining = n;
    T cur_factor = 2;
    while (remaining > 1) {
        int factor_count = 0;
        while (remaining % cur_factor == 0) {
            remaining /= cur_factor;
            factor_count++;
        }
        if (factor_count) {
            factorization[cur_factor] = factor_count;
        }
        cur_factor += cur_factor == 2 ? 1 : 2;
    }
    return factorization;
}

template<typename T> T _product(vector<T> primes, vector<int> powers) {
    T product = 1;
    for (int i = 0; i < primes.size(); i++) {
        product *= int_pow(primes[i], powers[i]);
    }
    return product;
}

template<typename T> T sum_divisors_brute_force(T n) {
    T divisor_sum = 0;
    T n_sqrt = sqrt(n);
    for (T fac = 1; fac <= n_sqrt; fac++) {
        if (n % fac == 0) {
            divisor_sum += fac + (n / fac);
        }
    }
    if (n_sqrt * n_sqrt == n) {
        divisor_sum -= n_sqrt;
    }
    return divisor_sum;
}

template<typename T> T sum_divisors_from_factors(T n) {
    vector<pair<T, int>> factors = get_entries(prime_factorization(n));
    vector<int> power_vector(factors.size());
    vector<int> power_limits(factors.size());
    vector<T> primes(factors.size());
    for (int i = 0; i < factors.size(); i++) {
        power_limits[i] = factors[i].second + 1;
        primes[i] = factors[i].first;
    }
    T divisor_sum = 0;
    do {
        divisor_sum += _product(primes, power_vector);
    } while (increment_vector(power_vector, power_limits));
    return divisor_sum;
}

template<typename T> int num_divisors(T n) {
    int n_divisors = 1;
    T remaining = n;
    T cur_factor = 2;
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

template<typename T> T sum_of_squares(T n) {
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
        BigInteger(const long number) : BigInteger(std::to_string(number)) {}
        BigInteger(const string& number) {
            data = zero();
            for (int i = 0; i < number.length(); i++) {
                _times(data, 10, BASE);
                _add(data, number[i] - '0', BASE);
            }
        }
        BigInteger times(unsigned int by) {
            BigInteger copy = *this;
            copy._times(copy.data, by, BASE);
            return copy;
        }
        BigInteger add(const BigInteger& big) {
            BigInteger copy = *this;
            copy._add(copy.data, big.data, BASE);
            return copy;
        }
        int compare(const BigInteger& big) {
            vector<unsigned int> this_data = this->data;
            vector<unsigned int> that_data = big.data;
            if (this_data.size() > that_data.size()) {
                return 1;
            } else if (this_data.size() < that_data.size()) {
                return -1;
            } else {
                for (int i = this_data.size() - 1; i >= 0; i++) {
                    if (this_data[i] > that_data[i]) {
                        return 1;
                    } else if (this_data[i] < that_data[i]) {
                        return -1;
                    }
                }
                return 0;
            }
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
