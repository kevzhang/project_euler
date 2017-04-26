#include <iostream>

using namespace std;

static const int LIMIT = 999;

int multiple_sum(int multiple, int limit) {
	int nElements = limit / multiple;
	int top = multiple * nElements;
	return (top + multiple) * nElements / 2;
}

int main() {
	cout << multiple_sum(3, LIMIT) + multiple_sum(5, LIMIT) - multiple_sum(15, LIMIT) << endl;
	return 0;
}
