import sieve, itertools

target = 8
limit = 100 * 1000
prime_list = sieve.compute_primes(limit)
prime_set = set(prime_list)

signatures = set()
for prime in prime_list:
	num_str = str(prime)
	size = len(num_str)
	for replace_size in range(size):
		# last digit is not candidate for replacement
		for replace_indices in itertools.combinations(range(size - 1), replace_size):
			digits = list(num_str)
			for replace_index in replace_indices:
				digits[replace_index] = "*"
			cur_signature = "".join(digits)
			if cur_signature in signatures:
				continue
			signatures.add(cur_signature)
			family_size = 0
			lowest_family_member = None
			for replace_digit in range(10):
				if replace_digit == 0 and 0 in replace_indices:
					continue
				for replace_index in replace_indices:
					digits[replace_index] = str(replace_digit)
				resulting_number = int("".join(digits))
				if resulting_number in prime_set:
					if lowest_family_member == None:
						lowest_family_member = resulting_number
					family_size += 1
				else:
					if (family_size + (9 - replace_digit)) < target:
						break
			if family_size == target:
				print lowest_family_member
				exit(0)

