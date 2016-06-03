import math

limit = 1000 * 1000

def choose(n, r):
	return math.factorial(n) / (math.factorial(r) * math.factorial(n - r))

# 23 given in the problem
total = 0
for n in range(23, 101):
	for r in range(1, n/2):
		if choose(n, r) > limit:
			total += n - 2 * r + 1
			break
print total

