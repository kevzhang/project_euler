#include <iostream>
#include <vector>
#include "maths.cpp"
#include "files.cpp"
#include "strings.cpp"

using namespace std;

static const int LIMIT = 10;

int main() {
	string input = read_file_as_string("inputs/013.txt");
	vector<string> lines;
	split(input, '\n', lines);
	BigInteger result = BigInteger::ZERO();
	for (int i = 0; i < lines.size(); i++) {
		BigInteger to_add(lines[i]);
		result = result.add(to_add);
	}
	cout << result.to_string().substr(0, LIMIT) << endl;
	return 0;
}
