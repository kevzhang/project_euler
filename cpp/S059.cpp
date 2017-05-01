#include <iostream>
#include "files.cpp"
#include "strings.cpp"

using namespace std;

static const int KEY_SIZE = 3;

inline bool is_letter(char c) {
    return (c >= 'a' && c <= 'z')
        || (c >= 'A' && c <= 'Z');
}

inline int ascii_sum_of_decrypted(const vector<char>& values, string key) {
    int sum = 0;
    for (int i = 0; i < values.size(); i++) {
        sum += key[i % KEY_SIZE] ^ values[i];
    }
    return sum;
}

inline int score_for_key(const vector<char>& values, char key, int skip) {
    int idx = skip;
    int score = 0;
    while (idx < values.size()) {
        score += is_letter(values[idx] ^ key);
        idx += KEY_SIZE;
    }
    return score;
}

int main() {
    vector<string> letters;
    split(read_file_as_string("inputs/059.txt"), ',', letters);
    vector<char> values;
    for (string letter : letters) {
        values.push_back(stoi(letter));
    }
    string key;
    for (int i = 0; i < KEY_SIZE; i++) {
        char best_key = -1;
        int best_score = -1;
        for (char c = 'a'; c <= 'z'; c++) {
            int score = score_for_key(values, c, i);
            if (score > best_score) {
                best_score = score;
                best_key = c;
            }
        }
        key += best_key;
    }
    cout << ascii_sum_of_decrypted(values, key) << endl;
    return 0;
}
