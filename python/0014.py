memoize = {1 : 1}

def advance(n):
	if n % 2 == 0:
		return n / 2
	else:
		return 3 * n + 1

def collatz(n):
	if n in memoize:
		return memoize[n]
	path = [n]
	while advance(n) not in memoize:
		n = advance(n)
		path.append(n)
	memoized_len = memoize[advance(n)]
	for i in range(len(path)):
		memoize[path[i]] = len(path) - i + memoized_len
	return memoize[path[0]]

for start in range(2, 1000 * 1000):
	collatz(start)

max_len = max(memoize.values())
longest = [x for x in memoize.items() if x[1] is max_len]	
assert len(longest) is 1, "More than one max found"
print longest[0][0]
