import sieve, itertools

limit = 20000
prime_table = sieve.compute_sieve(limit)
def max_consecutive(a, b):
	consec = 0
	for n in itertools.count():
		if not prime_table[n*n + a*n + b]:
			return consec
		else:
			consec += 1

def solve():
	for a in range(-999, 1000):
		for b in range(-999, 1000):
			if not prime_table[b]:
				continue
			yield (a, b, max_consecutive(a,b))

best = max(solve(), key=lambda tup: tup[2])
print best[0] * best[1]

