import math

limit = 28124

def divisor_sum(n):
	sqrt = int(math.sqrt(n))
	return sum([(div + n / div) for div in range(1, sqrt + 1) if n % div == 0]) - n - sqrt * (sqrt ** 2 == n)

def abundant(n):
	return divisor_sum(n) > n

abundant_table = [False] + [abundant(n) for n in range(1, limit)]

def is_bad(n):
	for lower in range(1, n / 2 + 1):
		if abundant_table[lower] and abundant_table[n - lower]:
			return False
	return True

print sum([n for n in range(1, limit) if is_bad(n)])
			
