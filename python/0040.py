import itertools, operator

limit = 1000 * 1000
digits = list()

for i in itertools.count(1):
	digits.extend(str(i))
	if len(digits) > limit:
		break

print reduce(operator.mul, (int(digits[10 ** i - 1]) for i in range(7)))

