
value_map = {}
for i in range(2,10):
	value_map[str(i)] = i
value_map["T"] = 10
value_map["J"] = 11
value_map["Q"] = 12
value_map["K"] = 13
value_map["A"] = 14

base = len(value_map)

def line_to_hand_pair(line):
	cards = line.split(" ")
	p1 = cards[:5]
	p2 = cards[5:]
	return (p1, p2)

def is_consecutive(values):
	values.sort()
	for i in range(len(values) - 1):
		if values[i + 1] - values[i] != 1:
			return False
	return True

def only_values(hand):
	return [card[0] for card in hand]

def hand_by_suit(hand):
	res = {}
	for card in hand:
		if card[1] not in res:
			res[card[1]] = []
		res[card[1]].append(card)
	return res

def value_histogram(hand):
	value_counts = [0] * 15
	values = only_values(hand)
	numeric_values = [value_map[value] for value in values]
	for num in numeric_values:
		value_counts[num] += 1
	return value_counts

def get_high_card_value(hand):
	values = only_values(hand)
	numeric_values = [value_map[value] for value in values]
	return max(numeric_values)

def is_royal_flush(hand):
	by_suit = hand_by_suit(hand)
	for suit in by_suit:
		suit_hand = by_suit[suit]
		values = only_values(suit_hand)
		if "T" in values and "J" in values and "Q" in values\
			and "K" in values and "A" in values:
			return get_high_card_value(hand)
	return 0

def is_straight_flush(hand):
	by_suit = hand_by_suit(hand)
	if len(by_suit) > 1:
		return 0
	for suit in by_suit:
		suit_hand = by_suit[suit]
		values = only_values(suit_hand)
		numeric_values = [value_map[value] for value in values]
		if is_consecutive(numeric_values):
			return get_high_card_value(hand)
	return 0

def is_four_of_a_kind(hand):
	histogram = value_histogram(hand)
	if len(filter(lambda v : v == 4, histogram)) == 1:
		return histogram.index(4)
	else:
		return 0

def is_full_house(hand):
	histogram = value_histogram(hand)
	three = len(filter(lambda v : v == 3, histogram)) == 1
	two = len(filter(lambda v : v == 2, histogram)) == 1
	if three and two:
		return base * histogram.index(3) + histogram.index(2)
	else:
		return 0

def is_flush(hand):
	by_suit = hand_by_suit(hand)
	if len(by_suit) == 1:
		return get_high_card_value(hand)
	else:
		return 0

def is_straight(hand):
	values = only_values(hand)
	numeric_values = [value_map[value] for value in values]
	if is_consecutive(numeric_values):
		return get_high_card_value(hand)
	else:
		return 0

def is_three_of_a_kind(hand):
	histogram = value_histogram(hand)
	if len(filter(lambda v : v == 3, histogram)) == 1:
		return histogram.index(3)
	else:
		return 0

def is_two_pairs(hand):
	histogram = value_histogram(hand)
	if len(filter(lambda v : v == 2, histogram)) == 2:
		return len(histogram) - 1 - histogram[::-1].index(2)
	else:
		return 0

def is_one_pair(hand):
	histogram = value_histogram(hand)
	if len(filter(lambda v : v == 2, histogram)) == 1:
		return histogram.index(2)
	else:
		return 0

def get_high_card_score(hand):
	values = only_values(hand)
	numeric_values = [value_map[value] for value in values]
	numeric_values.sort()
	score = 0
	for num in numeric_values[::-1]:
		score *= base
		score += num
	return score

def get_score(hand):
	super_base = 10 ** 9
	score = 0
	score += is_royal_flush(hand) * (super_base ** 10)
	score += is_straight_flush(hand) * (super_base ** 9)
	score += is_four_of_a_kind(hand) * (super_base ** 8)
	score += is_full_house(hand) * (super_base ** 7)
	score += is_flush(hand) * (super_base ** 6)
	score += is_straight(hand) * (super_base ** 5)
	score += is_three_of_a_kind(hand) * (super_base ** 4)
	score += is_two_pairs(hand) * (super_base ** 3)
	score += is_one_pair(hand) * (super_base ** 2)
	score += get_high_card_score(hand)
	return score

hands_file = open("inputs/p054_poker.txt")
hand_pairs = map(line_to_hand_pair, hands_file.read().split("\n")[:-1])
hands_file.close()

p1_total = 0

for hand_pair in hand_pairs:
	p1_score = get_score(hand_pair[0])
	p2_score = get_score(hand_pair[1])
	if (p1_score > p2_score):
		p1_total += 1
	elif (p1_score == p2_score):
		raise Exception("tied with " + str(p1_score) + " and " + str(p2_score))

print p1_total
