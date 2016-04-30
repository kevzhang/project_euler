import math

n = 1000 * 1000 - 1
digits = range(10)
output = ""

for i in range(9, -1, -1):
	pseudo_digit = n / math.factorial(i)
	output += str(digits[pseudo_digit])
	n -= pseudo_digit * math.factorial(i)
	digits.remove(digits[pseudo_digit])

print output

