#include <fstream>

using namespace std;

const string read_file_as_string(string path) {
	ifstream reader(path);
	string s;
	if (reader) {
		char c;
		while (!reader.eof()) {
			reader.get(c);
			s += c;
		}
	}
	reader.close();
    // remove trailing newlines
    while (!s.empty() && s.back() == '\n') {
        s.pop_back();
    }
	return s;
}