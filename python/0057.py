from fractions import Fraction

limit = 1000

cur_expansion = Fraction(1, 1)

total = 0
for i in range(limit):
	cur_expansion = 1 + 1/(1 + cur_expansion)
	if len(str(cur_expansion.numerator)) > len(str(cur_expansion.denominator)):
		total += 1

print total

