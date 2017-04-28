#include <vector>
#include <sstream>

using namespace std;

void split(const string& str, char delim, vector<string>& output) {
	stringstream ss(str);
	string token;
	while (getline(ss, token, delim)) {
		output.push_back(token);
	}
}

int sum_digits(string number) {
    int sum = 0;
    for (int i = 0; i < number.length(); i++) {
        sum += number[i] - '0';
    }
    return sum;
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

template<typename T> string to_string(vector<T> vec) {
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

template<typename T> string to_string(T arr[], int N) {
	if (!N) {
		return "[]";
	}
	string s = "[";
	s += to_string(arr[0]);
	for (int i = 1; i < N; i++) {
		s += ", " + to_string(arr[i]);
	}
	s += ']';
	return s;
}
