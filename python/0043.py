import sieve, itertools

primes = sieve.compute_primes(7)

def to_number(digits):
	val = digits[0]
	for d in digits[1:]:
		val = 10 * val + d
	return val

total = 0
for pan_digits in itertools.permutations(range(10)):
	special = True
	for left_bound in range(1, 8):
		if to_number(pan_digits[left_bound: left_bound + 3]) % primes[left_bound - 1] != 0:
			special = False
			break
	if special:
		total += to_number(pan_digits)

print total

