import sieve, math

limit = 1000 * 1000

def rotate(num):
	power = math.log10(num)
	last_dig = num % 10
	return num / 10 + last_dig * (10 ** int(power))

# Faster than using a sieve table
def is_prime(n):
	if n == 1 or (n != 2 and n % 2 == 0):
		return False
	for div in range(3, int(math.sqrt(n)) + 1, 2):
		if n % div == 0:
			return False
	return True

total = 0
for i in range(2, limit):
	if not is_prime(i):
		continue
	else:
		size_i = int(math.log10(i))
		tmp_i = i
		add = True
		for rot in range(size_i):
			tmp_i = rotate(tmp_i)
			if not is_prime(tmp_i):
				add = False
				break
		total += add

print total
