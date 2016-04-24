
def divisor_sum(n):
	return sum([div for div in range(1, n) if n % div == 0])

print sum([amicable for amicable in range(1, 10001) if divisor_sum(amicable) \
	!= amicable and divisor_sum(divisor_sum(amicable)) == amicable])
		
	
