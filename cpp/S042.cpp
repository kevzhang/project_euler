#include <iostream>
#include "maths.cpp"
#include "files.cpp"
#include "strings.cpp"

using namespace std;

// same as S022.cpp
vector<string> extract_names(const string& input) {
    vector<string> names;
    split(input, ',', names);
    for (int i = 0; i < names.size(); i++) {
        // strip out surrounding quotes
        names[i] = names[i].substr(1, names[i].length() - 2);
    }
    return names;
}

inline int score(const string& name) {
    int score = 0;
    for (char c : name) {
        score += c - 'A' + 1;
    }
    return score;
}

inline bool is_triangle(int n) {
    int possible_idx = (sqrt(1 + 8 * n) - 1) / 2;
    return triangle(possible_idx) == n;
}

int main() {
    vector<string> names = extract_names(read_file_as_string("inputs/042.txt"));
    int count = 0;
    for (int i = 0; i < names.size(); i++) {
        count += is_triangle(score(names[i]));
    }
    cout << count << endl;
    return 0;
}
