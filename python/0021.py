import math

def divisor_sum(n):
	sqrt = int(math.sqrt(n))
	return sum([(div + n / div) for div in range(1, sqrt + 1) if n % div == 0]) - n - sqrt * (sqrt ** 2 == n)

print sum([amicable for amicable in range(1, 10001) if divisor_sum(amicable) \
	!= amicable and divisor_sum(divisor_sum(amicable)) == amicable])
		
	
