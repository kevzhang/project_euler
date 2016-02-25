def sum(multiple, bound):
	count = bound / multiple
	sum = count * (count * multiple + multiple) / 2
	return sum

print sum(5, 999) + sum(3, 999) - sum(15, 999)
