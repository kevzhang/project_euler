import sieve
limit = 1000 * 1000

sieve_table = sieve.compute_sieve(limit)
factors = [0] * limit

for maybe_prime in range(limit):
	if sieve_table[maybe_prime]:
		factors[maybe_prime] = 1
		for multiple in range(2 * maybe_prime, limit, maybe_prime):
			factors[multiple] += 1

consec = 0
for i in range(limit):
	if factors[i] == 4:
		consec += 1
	else:
		consec = 0
	if consec == 4:
		print i - 3
		break

