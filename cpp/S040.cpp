#include <iostream>
#include "maths.cpp"

using namespace std;

int get_digit(int n) {
    int group_size = 9, group_width = 1;
    while (n >= group_size * group_width) {
        n -= group_size * group_width;
        group_width++;
        group_size *= 10;
    }
    int digit_idx = (group_width - 1) - (n % group_width);
    int base = int_pow(10, group_width - 1);
    int number = base + n / group_width;
    for (int i = 0; i < digit_idx; i++) {
        number /= 10;
    }
    return number % 10;
}

int main() {
    int digit_prod = 1;
    for (int exponent = 0; exponent < 7; exponent++) {
        digit_prod *= get_digit(int_pow(10, exponent) - 1);
    }
    cout << digit_prod << endl;
    return 0;
}
