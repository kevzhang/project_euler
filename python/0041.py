import itertools, prime

def to_number(digit_strs):
	return int("".join(digit_strs))

# sum of (1, ..., 8) and (1, ..., 9) are multiples of 3
for size in range(7, 0, -1):
	for digit_strs in itertools.permutations((str(d) for d in range(size, 0, -1))):
		if prime.is_prime(to_number(digit_strs)):
			print to_number(digit_strs)
			exit(0)

