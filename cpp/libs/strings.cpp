#include <vector>
#include <sstream>

void split(const string& str, char delim, vector<string>& output) {
	stringstream ss(str);
	string token;
	while (getline(ss, token, delim)) {
		output.push_back(token);
	}
}
