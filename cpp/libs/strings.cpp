#include <vector>
#include <sstream>

void split(const string& str, char delim, vector<string>& output) {
	stringstream ss(str);
	string token;
	while (getline(ss, token, delim)) {
		output.push_back(token);
	}
}

string to_string(vector<string> vec) {
	if (vec.empty()) {
		return "[]";
	}
	string s = "[";
	s += vec[0];
	for (int i = 1; i < vec.size(); i++) {
		s += ", " + vec[i];
	}
	s += ']';
	return s;
}

string to_string(vector<int> vec) {
	if (vec.empty()) {
		return "[]";
	}
	string s = "[";
	s += to_string(vec[0]);
	for (int i = 1; i < vec.size(); i++) {
		s += ", " + to_string(vec[i]);
	}
	s += ']';
	return s;
}
