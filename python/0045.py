import itertools

def triangle(n):
	return n * (n + 1) / 2

def pentagon(n):
	return n * (3 * n - 1) / 2

def hexagon(n):
	return n * (2 * n - 1)

# starting numbers given in problem spec
tri_idx = 285
pent_idx = 165
for hex_idx in itertools.count(144):
	cur_hex = hexagon(hex_idx)
	while triangle(tri_idx) < cur_hex:
		tri_idx += 1
	while pentagon(pent_idx) < cur_hex:
		pent_idx += 1
	if cur_hex == triangle(tri_idx) == pentagon(pent_idx):
		print cur_hex
		break

