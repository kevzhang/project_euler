#include <iostream>
#include <vector>
#include <algorithm>
#include "files.cpp"
#include "strings.cpp"

using namespace std;

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

int main() {
    vector<string> names = extract_names(read_file_as_string("inputs/022.txt"));
    sort(names.begin(), names.end());
    int total_score = 0;
    for (int i = 0; i < names.size(); i++) {
        total_score += (i + 1) * score(names[i]);
    }
    cout << total_score << endl;
    return 0;
}
