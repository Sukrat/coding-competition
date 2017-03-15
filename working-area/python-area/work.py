import itertools

suits = ["C", "D", "H", "S"]
types = ["A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2"]
game_values = [14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2]
cards = [[suit, types[i], game_values[i]]
         for suit in suits for i in range(0, len(types))]


def type_of_hand(hand):
    suit_map = dict()
    type_map = dict()
    for h in hand:
        suit_map[h[0]] = suit_map.get(h[0], 0) + 1
        type_map[h[2]] = type_map.get(h[2], 0) + 1


hand_combs = []
for card in itertools.combinations(cards, 5):
    hand_combs.append(card)

for hand in hand_combs:
    type_of_hand(hand)
print(len(hand_combs))
