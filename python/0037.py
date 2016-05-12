import sieve, math

cap = 1000 * 1000
prime_table = sieve.compute_sieve(cap)

def special(n):
	if not prime_table[n]:
		return False
	num_size = 10 ** int(math.log10(n))
	while num_size > 1:
		if not prime_table[n % num_size]:
			return False
		num_size /= 10
	n /= 10
	while n > 0:
		if not prime_table[n]:
			return False
		n /= 10
	return True

total = 0
count = 0
for n in range(10, cap):
	if special(n):
		total += n
		count += 1
	if count == 11:
		print total
		exit(0)

print "fail"
