import collections.Pair;
import collections.PeCollections;
import com.google.common.base.Splitter;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.math.LongMath;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class S054 {

    private static final int HAND_SIZE = 5;

    public static int solve() {
        List<Pair<String[], String[]>> rounds = parseInput(PeResources.getResource("054.txt"));
        return (int) rounds.stream()
                .filter(pair -> scoreHand(pair.getLeft()) > scoreHand(pair.getRight()))
                .count();
    }

    private static long scoreHand(String[] hand) {
        int base = 14;
        String[] sortedHand = sortByRank(hand);
        long score = 0;
        int straightRank = getStraightRank(sortedHand);
        int flushRank = getFlushRank(sortedHand);
        if (straightRank > 0 && flushRank > 0) {
            score += LongMath.pow(base, 8) * straightRank;
        }
        score += LongMath.pow(base, 7) * getQuadsRank(sortedHand);
        score += LongMath.pow(base, 6) * getFullHouseRank(sortedHand);
        score += LongMath.pow(base, 5) * flushRank;
        score += LongMath.pow(base, 4) * straightRank;
        score += LongMath.pow(base, 3) * getTripsRank(sortedHand);
        score += LongMath.pow(base, 2) * getTwoPairsRank(sortedHand);
        score += LongMath.pow(base, 1) * getPairsRank(sortedHand);
        score += LongMath.pow(base, 0) * getHighRank(sortedHand);
        return score;
    }

    private static int getHighRank(String[] sortedHand) {
        return getRank(sortedHand[HAND_SIZE - 1].charAt(0));
    }

    private static int getPairsRank(String[] sortedHand) {
        HashMultiset<Character> characters = HashMultiset.create();
        for (String card : sortedHand) {
            characters.add(card.charAt(0));
        }
        List<Multiset.Entry<Character>> pairs = characters.entrySet().stream()
                .filter(entry -> entry.getCount() == 2)
                .collect(Collectors.toList());
        if (pairs.size() != 1) {
            return 0;
        } else {
            return getRank(pairs.get(0).getElement());
        }
    }

    private static int getTwoPairsRank(String[] sortedHand) {
        HashMultiset<Character> characters = HashMultiset.create();
        for (String card : sortedHand) {
            characters.add(card.charAt(0));
        }
        List<Multiset.Entry<Character>> pairs = characters.entrySet().stream()
                .filter(entry -> entry.getCount() == 2)
                .collect(Collectors.toList());
        if (pairs.size() != 2) {
            return 0;
        } else {
            return Math.max(getRank(pairs.get(0).getElement()), getRank(pairs.get(1).getElement()));
        }
    }

    private static int getTripsRank(String[] sortedHand) {
        HashMultiset<Character> characters = HashMultiset.create();
        for (String card : sortedHand) {
            characters.add(card.charAt(0));
        }
        for (Multiset.Entry<Character> entry : characters.entrySet()) {
            if (entry.getCount() == 3) {
                return getRank(entry.getElement().charValue());
            }
        }
        return 0;
    }

    private static int getFullHouseRank(String[] sortedHand) {
        HashMultiset<Character> characters = HashMultiset.create();
        for (String card : sortedHand) {
            characters.add(card.charAt(0));
        }
        List<Multiset.Entry<Character>> entries = Lists.newArrayList(characters.entrySet());
        Collections.sort(entries, Comparator.comparingInt(Multiset.Entry::getCount));
        if (entries.size() != 2 || entries.get(0).getCount() != 2) {
            return 0;
        } else {
            return getRank(entries.get(1).getElement());
        }
    }

    private static int getQuadsRank(String[] sortedHand) {
        HashMultiset<Character> characters = HashMultiset.create();
        for (String card : sortedHand) {
            characters.add(card.charAt(0));
        }
        for (Multiset.Entry<Character> entry : characters.entrySet()) {
            if (entry.getCount() == 4) {
                return getRank(entry.getElement().charValue());
            }
        }
        return 0;
    }

    private static int getStraightRank(String[] sortedHand) {
        for (int i = 0; i < HAND_SIZE - 1; i++) {
            int nextRank = getRank(sortedHand[i + 1].charAt(0));
            int thisRank = getRank(sortedHand[i].charAt(0));
            if (nextRank - thisRank != 1) {
                return 0;
            }
        }
        return getRank(sortedHand[sortedHand.length - 1].charAt(0));
    }

    private static int getFlushRank(String[] sortedHand) {
        for (int i = 0; i < HAND_SIZE - 1; i++) {
            int nextSuit = sortedHand[i + 1].charAt(1);
            int thisSuit = sortedHand[i].charAt(1);
            if (nextSuit != thisSuit) {
                return 0;
            }
        }
        return getRank(sortedHand[sortedHand.length - 1].charAt(0));
    }

    private static String[] sortByRank(String[] hand) {
        String[] sortedHand = Arrays.copyOf(hand, hand.length);
        Arrays.sort(sortedHand, Comparator.comparingInt(card -> getRank(card.charAt(0))));
        return sortedHand;
    }

    private static int getRank(char rank) {
        switch (rank) {
            case '2': return 1;
            case '3': return 2;
            case '4': return 3;
            case '5': return 4;
            case '6': return 5;
            case '7': return 6;
            case '8': return 7;
            case '9': return 8;
            case 'T': return 9;
            case 'J': return 10;
            case 'Q': return 11;
            case 'K': return 12;
            case 'A': return 13;
            default: throw new IllegalArgumentException(Character.toString(rank));
        }
    }

    private static List<Pair<String[], String[]>> parseInput(String input) {
        List<Pair<String[], String[]>> hands = Lists.newArrayList();
        List<String> lines = Splitter.on('\n').splitToList(input);
        for (String line : lines) {
            if (line.isEmpty()) {
                break;
            }
            List<String> strings = Splitter.on(' ').splitToList(line);
            String[] p1Hand = PeCollections.toArray(strings.subList(0, HAND_SIZE));
            String[] p2Hand = PeCollections.toArray(strings.subList(HAND_SIZE, 2 * HAND_SIZE));
            hands.add(new Pair<>(p1Hand, p2Hand));
        }
        return hands;
    }
}
