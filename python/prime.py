import math

def is_prime(n):
	if n == 1:
		return False
	if n == 2 or n == 3:
		return True
	if n % 2 == 0 or n % 3 == 0:
		return False
	sq = int(math.sqrt(n))
	check = 5
	while (check <= sq):
		if n % check == 0 or n % (check + 2) == 0:
			return False
		check += 6
	return True
