from operator import mul

def prime_factors(n):
	assert n > 0, "Input is not positive"
	factors = {}
	remaining = n
	factor = 2
	while remaining > 1:
		if remaining % factor == 0:
			if factor not in factors:
				factors[factor] = 0
			factors[factor] += 1
			remaining /= factor
		else:
			factor += 1
	return factors

def num_factors(n):
	factors = prime_factors(n)
	return reduce(mul, [factors[key] + 1 for key in factors], 1)

