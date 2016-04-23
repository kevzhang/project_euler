letter_counts = {
	1: len("one"),
	2: len("two"),
	3: len("three"),
	4: len("four"),
	5: len("five"),
	6: len("six"),
	7: len("seven"),
	8: len("eight"),
	9: len("nine"),
	10: len("ten"),
	11: len("eleven"),
	12: len("twelve"),
	13: len("thirteen"),
	14: len("fourteen"),
	15: len("fifteen"),
	16: len("sixteen"),
	17: len("seventeen"),
	18: len("eighteen"),
	19: len("nineteen"),
	20: len("twenty"),
	30: len("thirty"),
	40: len("forty"),
	50: len("fifty"),
	60: len("sixty"),
	70: len("seventy"),
	80: len("eighty"),
	90: len("ninety"),
	100: len("hundred"),
	1000: len("thousand")} 

def letters(n):
	if n == 0:
		return 0
	if n in letter_counts and n not in [100, 1000]:
		return letter_counts[n]
	base = 10 ** (len(str(n)) - 1)
	digit = int(str(n)[0])
	recur = letters(n - base * digit)
	if base == 1000:
		return letter_counts[digit] + letter_counts[base] + recur
	elif base == 100:
		if recur > 0:
			return letter_counts[digit] + letter_counts[base] + len("and") + recur
		else:
			return letter_counts[digit] + letter_counts[base]
	else:
		return letter_counts[base * digit] + recur

print sum([letters(x) for x in range(1,1001)])
