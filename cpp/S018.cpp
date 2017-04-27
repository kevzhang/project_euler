#include <iostream>
#include <vector>
#include <sstream>
#include "files.cpp"
#include "strings.cpp"

using namespace std;

vector<vector<int> > get_grid(vector<string>& lines) {
    vector<vector<int> > grid;
    for (int i = 0; i < lines.size(); i++) {
        vector<int> row;
        stringstream ss(lines[i]);
        string number;
        while (getline(ss, number, ' ')) {
            row.push_back(stoi(number));
        }
        grid.push_back(row);
    }
    return grid;
}

int max_path_sum(vector<vector<int> > grid) {
    int N = grid.back().size();
    vector<int> dp = grid.back();
    vector<int> next_dp = dp;
    for (int row = N - 2; row >= 0; row--) {
        for (int col = 0; col < row + 1; col++) {
            next_dp[col] = max(dp[col], dp[col + 1]) + grid[row][col];
        }
        dp.swap(next_dp);
    }
    return dp.front();
}

int main() {
    vector<string> lines;
    split(read_file_as_string("inputs/018.txt"), '\n', lines);
    vector<vector<int> > grid = get_grid(lines);
    cout << max_path_sum(grid) << endl;
    return 0;
}
