days = {
	1:31,
	2:28,
	3:31,
	4:30,
	5:31,
	6:30,
	7:31,
	8:31,
	9:30,
	10:31,
	11:30,
	12:31}

def advance(dow, days):
	return (dow + days) % 7

def num_days(month, year):
	if (year % 400 == 0) or (year % 4 == 0 and year % 100 != 0):
		if month == 2:
			return days[month] + 1
		else:
			return days[month]
	else:
		return days[month]
		
# Jan 1, 1901 = Tuesday
count = 0
dow = 1
for year in range(1901, 2001):
	for month in range(1, 13):
		if dow == 6:
			count += 1
		dow = advance(dow, num_days(month, year))

print count
