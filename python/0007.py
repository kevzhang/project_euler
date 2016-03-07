from math import log, sqrt

def nth_prime(n):
	sieve_size = int(n * log(n, 2))
	sieve = [True] * sieve_size
	sieve[0] = False
	sieve[1] = False
	sieve[2] = True
	for x in range(2, int(sqrt(sieve_size)) + 2):
		if sieve[x]:
			for not_prime in (x*k for k in range(2, sieve_size) if x*k < sieve_size):
				sieve[not_prime] = False
	found = 0
	for x in range(2, sieve_size):
		if sieve[x]:
			found += 1
			if found == n:
				return x
	return None

print nth_prime(10001)
