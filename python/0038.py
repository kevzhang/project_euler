
def pan_concat(n):
	digits = set()
	concat = list()
	for i in range(1, 10):
		cur_digits = str(n * i)
		if "0" not in cur_digits and len(cur_digits) == len(set(cur_digits)) and digits.isdisjoint(cur_digits):
			digits.update(cur_digits)
			concat.append(str(n * i))
		else:
			break
	if len(digits) == 9:
		return int("".join(concat))
	else:
		return None

print max(pan_concat(n) for n in range(1, 100000))

