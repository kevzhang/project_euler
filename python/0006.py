def sum_of_squares(n):
	sum = 0
	for x in range(n):
		sum += (x+1) * (x+1)
	return sum

def square_of_sum(n):
	sum = (1 + n) * n / 2
	return sum * sum

print square_of_sum(100) - sum_of_squares(100)
