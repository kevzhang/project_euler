from math import sqrt

def compute_sieve(size):
	sieve = [True] * size
	sieve[0] = False
	sieve[1] = False
	sieve[2] = True
	for x in range(2, int(sqrt(size)) + 2):
		if sieve[x]:
			for not_prime in (x*k for k in range(2, (size-1) / x + 1)):
				sieve[not_prime] = False
	return sieve
