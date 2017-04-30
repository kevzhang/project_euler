#include <iostream>
#include "maths.cpp"

using namespace std;

int main() {
    // given
    long t_idx = 286, p_idx = 166, h_idx = 144;
    while (true) {
        long max_val = max(triangle(t_idx), max(pentagonal(p_idx), hexagonal(h_idx)));
        while (triangle(t_idx) < max_val) {
            t_idx++;
        }
        while (pentagonal(p_idx) < max_val) {
            p_idx++;
        }
        while (hexagonal(h_idx) < max_val) {
            h_idx++;
        }
        if (triangle(t_idx) == pentagonal(p_idx) && pentagonal(p_idx) == hexagonal(h_idx)) {
            cout << triangle(t_idx) << endl;
            return 0;
        }
    }
}
