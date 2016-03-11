import sieve

cap = 2000000
sieve_arr = sieve.compute_sieve(cap)
msum = 0
for x in range(cap):
	if sieve_arr[x]:
		msum += x

print msum
