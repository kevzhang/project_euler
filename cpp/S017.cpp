#include <iostream>
#include <vector>

using namespace std;

static const int LIMIT = 1000;

inline void add_word_length(vector<pair<int, int> >& vec, int number, string word) {
    vec.push_back(make_pair(number, word.length()));
}

vector<pair<int, int> > get_word_lengths() {
    vector<pair<int, int> > word_lengths;
    add_word_length(word_lengths, 1000, string("thousand"));
    add_word_length(word_lengths, 100, string("hundred"));
    add_word_length(word_lengths, 90, string("ninety"));
    add_word_length(word_lengths, 80, string("eighty"));
    add_word_length(word_lengths, 70, string("seventy"));
    add_word_length(word_lengths, 60, string("sixty"));
    add_word_length(word_lengths, 50, string("fifty"));
    add_word_length(word_lengths, 40, string("forty"));
    add_word_length(word_lengths, 30, string("thirty"));
    add_word_length(word_lengths, 20, string("twenty"));
    add_word_length(word_lengths, 19, string("nineteen"));
    add_word_length(word_lengths, 18, string("eighteen"));
    add_word_length(word_lengths, 17, string("seventeen"));
    add_word_length(word_lengths, 16, string("sixteen"));
    add_word_length(word_lengths, 15, string("fifteen"));
    add_word_length(word_lengths, 14, string("fourteen"));
    add_word_length(word_lengths, 13, string("thirteen"));
    add_word_length(word_lengths, 12, string("twelve"));
    add_word_length(word_lengths, 11, string("eleven"));
    add_word_length(word_lengths, 10, string("ten"));
    add_word_length(word_lengths, 9, string("nine"));
    add_word_length(word_lengths, 8, string("eight"));
    add_word_length(word_lengths, 7, string("seven"));
    add_word_length(word_lengths, 6, string("six"));
    add_word_length(word_lengths, 5, string("five"));
    add_word_length(word_lengths, 4, string("four"));
    add_word_length(word_lengths, 3, string("three"));
    add_word_length(word_lengths, 2, string("two"));
    add_word_length(word_lengths, 1, string("one"));
    return word_lengths;
}

int compute_word_length(vector<pair<int, int> > lengths, int n) {
    int remaining = n;
    int table_idx = 0;
    int total_length = 0;
    while (remaining > 0) {
        while (lengths[table_idx].first > remaining) {
            table_idx++;
        }
        int amount = lengths[table_idx].first;
        int length = lengths[table_idx].second;
        int scalar = remaining / amount;
        // scalar -> "one", "two", ...
        int scalar_length = amount >= 100 ? compute_word_length(lengths, scalar) : 0;
        total_length += scalar_length + length;
        remaining -= scalar * amount;
    }
    if (n > 100 && n % 100 != 0) {
        total_length += 3; // "and"
    }
    return total_length;
}

int main() {
    int sum = 0;
    vector<pair<int, int> > lengths = get_word_lengths();
    for (int i = 1; i <= LIMIT; i++) {
        sum += compute_word_length(lengths, i);
    }
    cout << sum << endl;
    return 0;
}
