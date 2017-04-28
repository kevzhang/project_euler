#include <iostream>
#include <vector>
#include <sstream>
#include "maths.cpp"
#include "files.cpp"
#include "strings.cpp"

using namespace std;

static const int CONSEC = 4;
static const int N = 20;

void get_grid(int grid[N][N], const vector<string>& lines) {
	for (int r = 0; r < N; r++) {
		stringstream ss(lines[r]);
		string number;
		int col = 0;
		while (getline(ss, number, ' ')) {
			grid[r][col++] = stoi(number);
		}
	}
}

int product(int grid[N][N], int r, int c, int dR, int dC) {
	int product = 1;
	for (int i = 0; i < CONSEC; i++) {
		if (0 <= r && r < N && 0 <= c && c < N) {
			product *= grid[r][c];
			r += dR;
			c += dC;
		} else {
			return -1;
		}
	}
	return product;
}

int main() {
	int arr[] = {0, 1, 2};
	const string input = read_file_as_string("inputs/011.txt");
	vector<string> lines;
	split(input, '\n', lines);
	int grid[N][N];
	get_grid(grid, lines);
	int max_prod = 1;
	for (int r = 0; r < N; r++) {
		for (int c = 0; c < N; c++) {
			int down = product(grid, r, c, 1, 0);
			int right = product(grid, r, c, 0, 1);
			int down_right = product(grid, r, c, 1, 1);
			int down_left = product(grid, r, c, 1, -1);
			max_prod = max_val(5, (int[]){max_prod, down, right, down_right, down_left});
		}
	}
	cout << max_prod << endl;
	return 0;
}
