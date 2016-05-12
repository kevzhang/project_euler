limit = 1000 * 1000

def is_pal(st):
	return st == st[::-1]

total = 0
for x in range(limit):
	if is_pal(str(x)) and is_pal(str(bin(x))[2:]):
		total += x

print total
