limit = 10 * 1000
max_iters = 50

def reverse_digits(number):
	return int(str(number)[::-1])

def is_palindrome(number):
	return str(number) == str(number)[::-1]

def is_lychrel(number, limit):
	base = number
	for i in range(limit):
		base = base + reverse_digits(base)
		if is_palindrome(base):
			return False
	return True

total = 0
for number in range(limit):
	if is_lychrel(number, max_iters):
		total += 1

print total

