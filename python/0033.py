from fractions import Fraction

fracs = set()
for num in range(10, 100):
	for denom in range(num + 1, 100):
		if num % 10 == 0 and denom % 10 == 0:
			continue
		else:
			n1 = num/10
			n2 = num % 10
			d1 = denom/10
			d2 = denom % 10
			frac = Fraction(num, denom)
			if d2 != 0 and n2 == d1 and Fraction(n1, d2) == frac or\
				d1 != 0 and n1 == d2 and Fraction(n2, d1) == frac:
				fracs.add(Fraction(num, denom))

print reduce(lambda x, y : x*y, fracs).denominator
