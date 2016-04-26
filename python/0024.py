import itertools
permutations = itertools.permutations(range(10))
millionth = next(itertools.islice(permutations, 1000 * 1000 - 1, 1000 * 1000))
print int("".join(map(str, millionth)))
