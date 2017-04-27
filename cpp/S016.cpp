#include <iostream>
#include <vector>
#include "maths.cpp"

using namespace std;

static const int EXP = 1000;
static const int BIG_INT_BLOCK_SIZE = sizeof(unsigned int) * 8;

int sum_digits(string number) {
    int sum = 0;
    for (int i = 0; i < number.length(); i++) {
        sum += number[i] - '0';
    }
    return sum;
}

int main() {
    vector<unsigned int> big_int_data((EXP / BIG_INT_BLOCK_SIZE) + 1);
    big_int_data.back() = 1 << (EXP % BIG_INT_BLOCK_SIZE);
    BigInteger result(big_int_data);
    cout << sum_digits(result.to_string()) << endl;
    return 0;
}
