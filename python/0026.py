import math
def cycle_len(n):
	numer = 10 ** int(math.ceil(math.log10(n)))
	remainders = set()
	while True:
		remain = numer % n
		if remain == 0:
			return 0
		if remain in remainders:
			return len(remainders)
		else:
			remainders.add(remain)
			numer = remain * 10

print max([(n, cycle_len(n)) for n in range(1, 1000)], key=lambda pair: pair[1])[0]
