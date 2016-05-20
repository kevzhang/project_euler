import math, itertools

def pentagon(n):
	return n * (3 * n - 1) / 2

def is_pentagon(n):
	candidate = int((1 + math.sqrt(1 + 24 * n)) / 6)
	return n == pentagon(candidate)

for upper_idx in itertools.count(2):
	for lower_idx in range(1, upper_idx):
		lower_penta = pentagon(lower_idx)
		upper_penta = pentagon(upper_idx)
		if is_pentagon(lower_penta + upper_penta) and is_pentagon(upper_penta - lower_penta):
			print upper_penta - lower_penta
			exit(0)

