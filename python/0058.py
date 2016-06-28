import itertools
import prime

diags = 1
primes = 0
for size in itertools.count(3, 2):
	diags += 4
	corners = [size ** 2 - k * (size - 1) for k in range(4)]
	primes += len(filter(prime.is_prime, corners))
	if primes * 10 < diags:
		print size
		exit(0)

