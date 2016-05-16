import math

word_file = open("inputs/p042_words.txt")
words = [word.strip('"') for word in word_file.read().split(",")]
word_file.close()

def is_triangle(n):
	candidate = int((1.0/2) * (math.sqrt(8 * n + 1) - 1))
	if n == candidate * (candidate + 1) / 2:
		return True
	else:
		return False

def word_val(word):
	return sum((ord(c) - ord("A") + 1 for c in word))

print sum((is_triangle(word_val(word)) for word in words))

