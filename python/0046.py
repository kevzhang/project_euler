import prime, itertools, math

for odd_composite in itertools.count(35, 2):
	if prime.is_prime(odd_composite):
		continue
	good = False
	for sq_idx in range(int(math.sqrt(odd_composite/2)) + 1):
		twice_square = 2 * (sq_idx ** 2)
		if prime.is_prime(odd_composite - twice_square):
			good = True
	if not good:
		print odd_composite
		break

