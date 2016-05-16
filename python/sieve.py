from math import sqrt, log

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

def compute_primes(nth):
	limit = int(nth * log(max(nth, 2)) + nth * (log(log(max(nth, 2), 2)) - 0.9385))
	if nth <= 10000:
		limit = max(100, limit * 2)
	sieve = compute_sieve(limit)
	primes = list()
	for i in range(limit):
		if len(primes)== nth:
			return primes
		if sieve[i]:
			primes.append(i)
	assert False, "not enough primes computed"

