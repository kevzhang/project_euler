import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

// TODO: way too bespoke
public class S051 {

    private static final int LIMIT = 1000 * 1000;

    public static int solve() {
        Set<Integer> primes = Sets.newLinkedHashSet(Maths.generatePrimesBelow(LIMIT));
        for (int prime : primes) {
            int[][] digitGroups = getDigitGroups(prime);
            for (int[] group : digitGroups) {
                maskLoop: for (int mask = 1; mask < (1 << group.length); mask++) {
                    List<Integer> resultingPrimes = Lists.newArrayList();
                    for (int replaceWith = 0; replaceWith < 10; replaceWith++) {
                        int replaced = replace(prime, group, mask, replaceWith);
                        if (primes.contains(replaced)) {
                            resultingPrimes.add(replaced);
                        }
                        if (resultingPrimes.size() < replaceWith - 1) {
                            continue maskLoop;
                        }
                    }
                    if (resultingPrimes.size() == 8) {
                        return resultingPrimes.get(0);
                    }
                }
            }
        }
        return -1;
    }

    private static int replace(int original, int[] locations, int mask, int replacementDigit) {
        int[] digits = Maths.getDigits(original);
        for (int locIdx = 0; locIdx < locations.length; locIdx++) {
            int replacementIdx = locations[locIdx];
            // Do not replace first digit with 0
            if (replacementIdx == 0 && replacementDigit == 0) {
                return -1;
            }
            if ((mask >>> locIdx) % 2 == 1) {
                digits[replacementIdx] = replacementDigit;
            }
        }
        return (int) Maths.digitToLong(digits);
    }

    private static int[][] getDigitGroups(int n) {
        int[] digitFrequency = Maths.getDigitFrequency(n);
        int[] digits = Maths.getDigits(n);
        int unique = 0;
        for (int count : digitFrequency) {
            if (count > 0) {
                unique++;
            }
        }
        int[][] digitGroups = new int[unique][];
        int idx = 0;
        for (int curDigit = 0; curDigit < digitFrequency.length; curDigit++) {
            int count = digitFrequency[curDigit];
            if (count > 0) {
                digitGroups[idx] = new int[count];
                int groupIdx = 0;
                for (int digitIdx = 0; digitIdx < digits.length; digitIdx++) {
                    int digit = digits[digitIdx];
                    if (digit == curDigit) {
                        digitGroups[idx][groupIdx++] = digitIdx;
                    }
                }
                idx++;
            }
        }
        return digitGroups;
    }
}

