def gcd(a, b):
	if a == 1:
		return 1
	elif a == 0:
		return b
	else:
		return gcd(b - (b/a) * a, a)

product = 1
for x in range(1, 21):
	adj = x / gcd(x, product)
	product *= adj

print product
