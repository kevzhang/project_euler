#include <iostream>
#include <set>
#include <map>
#include "maths.cpp"

using namespace std;

int main() {
    set<map<int, int>> s;
    for (int a = 2; a <= 100; a++) {
        map<int, int> factors = prime_factorization(a);
        for (int b = 2; b <= 100; b++) {
            map<int, int> scaled = factors;
            for (auto&& kv : scaled) {
                kv.second *= b;
            }
            s.insert(scaled);
        }
    }
    cout << s.size() << endl;
    return 0;
}
