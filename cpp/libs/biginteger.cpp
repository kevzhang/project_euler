#include <vector>

using namespace std;

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
        void times(unsigned int by) {
            _times(this->data, by, BASE);
        }
        void add(const BigInteger& big) {
            _add(this->data, big.data, BASE);
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
