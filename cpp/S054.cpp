#include <iostream>
#include <map>
#include <algorithm>
#include "files.cpp"
#include "strings.cpp"

using namespace std;

static map<char, int> RANKS = {
    {'2', 0}, {'3', 1}, {'4', 2}, {'5', 3}, {'6', 4}, {'7', 5},
    {'8', 6}, {'9', 7}, {'T', 8}, {'J', 9}, {'Q', 10}, {'K', 11}, {'A', 12}
};
static const int BASE = RANKS.size();

int of_a_kind(const vector<string>& hand, int k) {
    vector<char> rank_histogram(BASE);
    int score = 0;
    for (string card : hand) {
        rank_histogram[RANKS[card[0]]]++;
    }
    for (int i = BASE - 1; i >= 0; i--) {
        if (rank_histogram[i] == k) {
            score = score * BASE + i;
        }
    }
    return score;
}

int straight(const vector<string>& hand) {
    vector<char> rank_histogram(BASE);
    int max_rank = -1;
    int min_rank = BASE;
    int unique = 0;
    for (string card : hand) {
        rank_histogram[RANKS[card[0]]]++;
    }
    for (int i = BASE - 1; i >= 0; i--) {
        if (rank_histogram[i] == 1) {
            max_rank = max(max_rank, i);
            min_rank = min(min_rank, i);
            unique++;
        }
    }
    return (unique == 5) && (max_rank - min_rank == 4) ? max_rank : 0;
}

int flush(const vector<string>& hand) {
    int max_rank = RANKS[hand[0][0]];
    for (int i = 1; i < hand.size(); i++) {
        if (hand[i][1] != hand[i - 1][1]) {
            return 0;
        }
        max_rank = max(max_rank, RANKS[hand[i][0]]);
    }
    return max_rank;
}

int full_house(const vector<string>& hand) {
    return of_a_kind(hand, 3) * BASE * BASE + of_a_kind(hand, 2);
}

int straight_flush(const vector<string>& hand) {
    return (bool) flush(hand) * straight(hand);
}

int main() {
    vector<string> lines;
    split(read_file_as_string("inputs/054.txt"), '\n', lines);
    int p1_wins = 0;
    for (string line : lines) {
        vector<string> cards; split(line, ' ', cards);
        vector<string> p1(cards.begin(), cards.begin() + 5);
        vector<string> p2(cards.begin() + 5, cards.end());
        int p1_adv;
        if ((p1_adv = straight_flush(p1) - straight_flush(p2))) {
            p1_wins += p1_adv > 0;
        } else if ((p1_adv = of_a_kind(p1, 4) - of_a_kind(p2, 4))) {
            p1_wins += p1_adv > 0;
        } else if ((p1_adv = full_house(p1) - full_house(p2))) {
            p1_wins += p1_adv > 0;
        } else if ((p1_adv = flush(p1) - flush(p2))) {
            p1_wins += p1_adv > 0;
        } else if ((p1_adv = straight(p1) - straight(p2))) {
            p1_wins += p1_adv > 0;
        } else if ((p1_adv = of_a_kind(p1, 3) - of_a_kind(p2, 3))) {
            p1_wins += p1_adv > 0;
        } else if ((p1_adv = of_a_kind(p1, 2) - of_a_kind(p2, 2))) {
            p1_wins += p1_adv > 0;
        } else if ((p1_adv = of_a_kind(p1, 1) - of_a_kind(p2, 1))) {
            p1_wins += p1_adv > 0;
        }
    }
    cout << p1_wins << endl;
    return 0;
}
