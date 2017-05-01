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

int sum_digits(const string& number) {
    int sum = 0;
    for (int i = 0; i < number.length(); i++) {
        sum += number[i] - '0';
    }
    return sum;
}

inline bool is_palindrome(const string& str) {
    int lower = 0, upper = str.length() - 1;
	while (lower < upper) {
		if (str[lower] != str[upper]) {
			return false;
		}
		lower++;
		upper--;
	}
    return true;
}

string to_string(const vector<string>& vec) {
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

template<typename T> string to_string(const vector<T>& vec) {
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

template<typename T> string join(const string& join_by, vector<T> vec) {
	stringstream ss;
	for (auto i = 0; i < vec.size(); i++) {
		if (i != 0) {
			ss << join_by;
		}
		ss << to_string(vec[i]);
	}
	return ss.str();
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
