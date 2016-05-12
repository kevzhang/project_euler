import math

factorials = [math.factorial(k) for k in range(0, 10)]
limit = 26 * 100 * 1000
def fac_sum(num):
	return sum(factorials[ord(digit_str) - ord("0")] for digit_str in str(num))

total = 0
for num in range(10, limit):
	if fac_sum(num) == num:
		total += num

print total
