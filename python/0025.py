import itertools

first = 1
second = 1
for idx in itertools.count(3):
	tmp = second
	second = first + second
	first = tmp
	if len(str(second)) == 1000:
		print idx
		break
