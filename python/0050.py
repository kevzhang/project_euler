import sieve, math

limit = 1000 * 1000
prime_table = sieve.compute_sieve(limit)
entries = [(i, prime_table[i]) for i in range(limit)]
primes = [entry[0] for entry in entries if entry[1]]
num_primes = len(primes)

def partial_sums(arr):
	cur_total = 0
	yield cur_total
	for val in arr:
		cur_total += val
		yield cur_total

def interval_sum(partials, start, end):
	return partials[end] - partials[start]

partial_prime_sums = list(partial_sums(primes))

for interval_size in range(int(math.sqrt(limit * 2)), 0, -1):
	for start in range(0, num_primes + 1 - interval_size):
		interval = interval_sum(partial_prime_sums, start, start + interval_size)
		if interval > limit:
			break
		if not prime_table[interval]:
			continue
		print interval
		exit(0)

