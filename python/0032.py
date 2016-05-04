import itertools

def to_int(digits):
	val = digits[0]
	for d in digits[1:]:
		val = 10 * val + d
	return val

prods = set()
for perm in itertools.permutations(range(1, 10)):
	product = to_int(perm[5:])
	if product in prods:
		continue
	for split in range(1,5):
		m1 = to_int(perm[0:split])
		m2 = to_int(perm[split:5])
		if m1 * m2 == product:
			prods.add(product)
			break

print sum(prods)

