def isPal(arg):
	string = str(arg)
	return string == string[::-1]	

largest = -1
for x in range(1000):
	for y in range(1000):
		product = x * y
		if isPal(product):
			largest = max(largest, product)

print largest
		
		
