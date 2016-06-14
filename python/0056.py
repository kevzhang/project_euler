limit = 100

def digit_sum(number):
	return sum((int(digit) for digit in str(number)))

def solve():
	for a in range(limit):
		for b in range(limit):
			yield digit_sum(a ** b)

print max(solve())

