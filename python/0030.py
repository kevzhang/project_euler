total = 0
for n in range(1, 1000 * 1000):
	if n == sum((int(c) ** 5 for c in str(n))):
		total += n
print total - 1
	
