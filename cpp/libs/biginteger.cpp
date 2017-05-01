#include <vector>

using namespace std;

class BigInteger {
    private:
        uint64_t BASE = 1L << 32;
        // little endian, in increasing order of bits
        vector<uint32_t> data;
        void _add(vector<uint32_t>& number, const vector<uint32_t>& by, unsigned long base) {
            uint32_t carry = 0;
            int common_length = min(number.size(), by.size());
            // add common bits
            for (int i = 0; i < common_length; i++) {
                unsigned long result = ((unsigned long)number[i]) + by[i] + carry;
                number[i] = (uint32_t) (result % base);
                carry = result / base;
            }
            // my number is bigger
            for (int i = common_length; carry && i < number.size(); i++) {
                unsigned long result = ((unsigned long)number[i]) + carry;
                number[i] = (uint32_t) (result % base);
                carry = result / base;
            }
            // their number is bigger
            for (int i = common_length; i < by.size(); i++) {
                unsigned long result = ((unsigned long)by[i]) + carry;
                number.push_back((uint32_t) (result % base));
                carry = result / base;
            }
            // finish adding carry amounts
            while (carry) {
                number.push_back(carry % base);
                carry /= base;
            }
        }
        // TODO: unsafe, careful to not overflow the uint64_t
        void _add(vector<uint32_t>& number, uint64_t by, unsigned long base) {
            uint32_t carry = by;
            for (int i = 0; i < number.size(); i++) {
                unsigned long result = ((unsigned long)number[i]) + carry;
                number[i] = (uint32_t) (result % base);
                carry = result / base;
            }
            while (carry) {
                number.push_back(carry % base);
                carry /= base;
            }
        }
        // TODO: unsafe, careful to not overflow the uint64_t
        void _times(vector<uint32_t>& number, uint64_t by, unsigned long base) {
            uint32_t carry = 0;
            for (int i = 0; i < number.size(); i++) {
                unsigned long result = ((unsigned long)number[i]) * by + carry;
                number[i] = (uint32_t) (result % base);
                carry = result / base;
            }
            while (carry) {
                number.push_back(carry % base);
                carry /= base;
            }
        }
        static vector<uint32_t> zero() {
            vector<uint32_t> number;
            number.push_back(0);
            return number;
        }
        static vector<uint32_t> one() {
            vector<uint32_t> number;
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
        BigInteger(const vector<uint32_t>& vec) {
            data = vec;
        }
        BigInteger(const long number, uint32_t base) : BigInteger(std::to_string(number), base) {}
        BigInteger(const long number) : BigInteger(std::to_string(number)) {}
        BigInteger(const string& number) : BigInteger(number, ((uint64_t)1 << 32)) {}
        BigInteger(const string& number, uint64_t base) {
            this->BASE = base;
            data = zero();
            for (int i = 0; i < number.length(); i++) {
                _times(data, 10, BASE);
                _add(data, number[i] - '0', BASE);
            }
        }
        bool is_base_palindrome() {
            size_t lower = 0, upper = data.size() - 1;
            while (lower < upper) {
                if (data[lower] != data[upper]) {
                    return false;
                }
                lower++;
                upper--;
            }
            return true;
        }
        uint64_t sum_base() {
            uint64_t sum = 0;
            for (int i = 0; i < data.size(); i++) {
                sum += data[i];
            }
            return sum;
        }
        uint32_t num_base_digits() {
            return data.size();
        }
        // this->BASE == big.BASE
        void swap(BigInteger& big) {
            this->data.swap(big.data);
        }
        void reverse_base() {
            reverse(data.begin(), data.end());
        }
        void times(uint32_t by) {
            _times(this->data, by, BASE);
        }
        void add(const uint32_t by) {
            _add(this->data, by, BASE);
        }
        // this->BASE == big.BASE
        void add(const BigInteger& big) {
            _add(this->data, big.data, BASE);
        }
        // this->BASE == big.BASE
        int compare(const BigInteger& big) {
            vector<uint32_t> this_data = this->data;
            vector<uint32_t> that_data = big.data;
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
            vector<uint32_t> base_10 = zero();
            for (int idx = data.size() - 1; idx >= 0; idx--) {
                _times(base_10, BASE, 10);
                _add(base_10, data[idx], 10);
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
