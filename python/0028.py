def solve():
	for width in range(3, 1002, 2):
		area = width ** 2
		for corner in range(4):
			yield area - corner * (width - 1)
print 1 + sum(solve())

