import numpy
denoms = [1, 2, 5, 10, 20, 50, 100, 200]
coins = len(denoms)
total = 200
dp = numpy.zeros((coins + 1, total + 1))

for i in range(coins + 1):
	dp[i][0] = 1
for i in range(1, total + 1):
	dp[0][i] = 0

for r in range(1, coins + 1):
	for c in range(1, total + 1):
		if denoms[r - 1] <= c:
			dp[r][c] = dp[r - 1][c] + dp[r][c - denoms[r - 1]]
		else:
			dp[r][c] = dp[r - 1][c]

print int(dp[coins][total])

