input = 600851475143

factor = 2
largest = -1
while (input > 1):
	if (input % factor == 0):
		input /= factor
		largest = factor
	else:
		factor += 1

print factor
