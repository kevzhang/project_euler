import math, itertools

def status(a, b, p):
	a2 = a ** 2
	b2 = b ** 2
	c2 = (p - a - b) ** 2
	if (a2 + b2 > c2):
		return 1
	elif (a2 + b2 == c2):
		return 0
	elif (a2 + b2 < c2):
		return -1

max_a = 1 / (2 + math.sqrt(2))
def num_soln(p):
	soln = 0
	b = 1
	for a in range(1, int(p * max_a) + 1):
		for b in itertools.count(b):
			if status(a, b, p) > 0:
				break
		for b in range(b - 1, a, -1):
			stat = status(a, b, p)
			if stat < 0:
				break
			elif stat == 0:
				soln += 1
				break
	return soln

print max(([p, num_soln(p)] for p in range(1, 1001)), key=lambda x: x[1])[0]

