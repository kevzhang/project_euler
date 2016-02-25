sum = 0
left = 1
right = 2
while (True):
	if (left > 4000000):
		break
	if (left % 2 == 0):
		sum += left
	leftTemp = left
	left = right
	right = leftTemp + right

print sum
