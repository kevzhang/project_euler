#include <iostream>
#include "files.cpp"

using namespace std;

long product(string s, int start_idx, int end_idx) {
	long prod = 1;
	for (int i = start_idx; i < end_idx; i++) {
		prod *= (s[i] - '0');
	}
	return prod;
}

int main() {
	string input = read_file_as_string("inputs/008.txt");
	long max_prod = 1;
	for (int start = 0; start < input.length() - 12; start++) {
		max_prod = max(max_prod, product(input, start, start + 13));
	}
	cout << max_prod << endl;
	return 0;
}
