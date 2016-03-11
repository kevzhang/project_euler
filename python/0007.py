from math import log, sqrt
import sieve

def nth_prime(n):
	sieve_size = int(n * log(n, 2))
	sieve_arr = sieve.compute_sieve(sieve_size)
	found = 0
	for x in range(2, sieve_size):
		if sieve_arr[x]:
			found += 1
			if found == n:
				return x
	return None

print nth_prime(10001)
