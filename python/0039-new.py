import math, itertools

limit = 1000
table = [0] * (limit + 1)
max_a = 1 / (2 + math.sqrt(2))
for a in range(1, int(limit * max_a) + 1):
	for b in itertools.count(a):
		a2 = a ** 2
		b2 = b ** 2
		c2 = a2 + b2
		c = int(math.sqrt(c2))
		if a + b + c > limit:
			break
		elif c2 == c ** 2:
			table[a + b + c] += 1

print table.index(max(table))

