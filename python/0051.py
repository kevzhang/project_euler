import sieve, itertools

target = 8
limit = 100 * 1000
prime_list = sieve.compute_primes(limit)
prime_set = set(prime_list)

def replace_multiple(arr, replace_indices, replace_val):
	for idx in replace_indices:
		arr[idx] = replace_val

signatures = set()
for prime in prime_list:
	num_str = str(prime)
	size = len(num_str)
	for replace_size in range(size):
		# last digit is not candidate for replacement
		for replace_indices in itertools.combinations(range(size - 1), replace_size):
			digits = list(num_str)
			replace_multiple(digits, replace_indices, "*")
			cur_signature = "".join(digits)
			if cur_signature in signatures:
				continue
			signatures.add(cur_signature)
			family_members = []
			for replace_digit in range(10):
				if replace_digit == 0 and 0 in replace_indices:
					continue
				replace_multiple(digits, replace_indices, str(replace_digit))
				resulting_number = int("".join(digits))
				if resulting_number in prime_set:
					family_members.append(resulting_number)
				else:
					if (len(family_members) + (9 - replace_digit)) < target:
						break
			if len(family_members) == target:
				print family_members[0]
				exit(0)

