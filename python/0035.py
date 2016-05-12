import sieve, math

limit = 1000 * 1000
prime_table = sieve.compute_sieve(limit)

def rotate(num):
	power = math.log10(num)
	last_dig = num % 10
	return num / 10 + last_dig * (10 ** int(power))

total = 0
for i in range(2, limit):
	if not prime_table[i]:
		continue
	else:
		size_i = int(math.log10(i))
		tmp_i = i
		add = True
		for rot in range(size_i):
			tmp_i = rotate(tmp_i)
			if not prime_table[tmp_i]:
				add = False
				break
		total += add

print total
