#include <iostream>

using namespace std;

int diag_sum(int half_width) {
    int sum = 1;
    for (int i = 0; i < half_width; i++) {
        int to_sq = (i + 1) * 2 + 1;
        int sq = to_sq * to_sq;
        sum += (sq + sq - 3 * (to_sq - 1)) * 2;
    }
    return sum;
}

int main() {
    cout << diag_sum(500) << endl;
    return 0;
}
