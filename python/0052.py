import itertools

def digit_set(number):
	return set(str(number))

# only consider numbers whose first digit is 1
for num in itertools.count(1):
	base = 10 ** num
	for tail in range(base):
		cur_num = base + tail
		cur_digits = digit_set(cur_num)
		found = True
		for multiplier in range(2, 7):
			if digit_set(cur_num * multiplier) != cur_digits:
				found = False
				break
		if found:
			print cur_num
			exit(0)

