from prime_factors import *

def triangle(n):
	return n * (n+1) / 2

cur_triangle = 1
goal = 500
while True:
	if num_factors(triangle(cur_triangle)) > goal:
		print triangle(cur_triangle)
		break
	else:
		cur_triangle += 1	
	
