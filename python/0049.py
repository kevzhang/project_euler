import sieve

prime_table = sieve.compute_sieve(10000)

def sorted_digits(num):
	chars = list(str(num))
	chars.sort()
	return chars

for first in range(1000, 10000):
	if not prime_table[first]:
		continue
	max_step = (10000 - first) / 2
	for step in range(1, max_step + 1):
		second = first + step
		third = first + 2 * step
		if (not prime_table[second]) or (not prime_table[third]):
			continue
		first_digits = sorted_digits(first)
		second_digits = sorted_digits(second)
		if first_digits != second_digits:
			continue
		third_digits = sorted_digits(third)
		if first_digits != third_digits:
			continue
		if first != 1487:
			print str(first) + str(second) + str(third)
			exit(0)

