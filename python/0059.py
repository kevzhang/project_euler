ciper_file = open("inputs/p059_cipher.txt");
cipher = map(int, ciper_file.readline().split(","))

def is_letter_value(n):
	return (ord('A') <= n and n <= ord('Z')) or (ord('a') <= n and n <= ord('z'))

def find_best_xor(offset_by):
	offset_cipher = cipher[offset_by::3]
	best_xor = -1
	most_letters = -1
	for xor_by in range(ord('a'), ord('z') + 1):
		decrypted_offset = [v ^ xor_by for v in offset_cipher]
		valid_letters = [ch for ch in decrypted_offset if is_letter_value(ch)]
		if len(valid_letters) > most_letters:
			most_letters = len(valid_letters)
			best_xor = xor_by
	return best_xor

ascii_sum = 0
for offset in range(3):
	offset_cipher = cipher[offset::3]
	xor = find_best_xor(offset)
	ascii_sum += sum([v ^ xor for v in offset_cipher])
print ascii_sum

