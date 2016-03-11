cap = 1000
for a in range(1, cap):
	for b in range(a, cap - a):
		c = cap - a - b
		if a**2 + b**2 == c**2:
			print a * b * c 
