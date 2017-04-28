#include <iostream>

using namespace std;

inline bool is_leap(int year) {
    return (year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0));
}

static const int N_DAYS[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
inline int days_in_month(int month, int year) {
    if (month == 1 && is_leap(year)) {
        return 29;
    } else {
        return N_DAYS[month];
    }
}

int main() {
    int dow = 1; // monday
    int count = 0;
    for (int year = 1900; year <= 2000; year++) {
        for (int month = 0; month < 12; month++) {
            count += (dow == 0) && (year >= 1901);
            dow = (dow + days_in_month(month, year)) % 7;
        }
    }
    cout << count << endl;
    return 0;
}
