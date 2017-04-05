import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class S049 {

    private static final int LIMIT = 10 * 1000;

    public static String solve() {
        ArrayList<Integer> primes = Maths.generatePrimesBelow(LIMIT);
        HashSet<Integer> primeSet = Sets.newHashSet(primes);
        int lowerPrimeIdx = -Collections.binarySearch(primes, 1000) - 1;
        for (int firstIdx = lowerPrimeIdx; firstIdx < primes.size() - 2; firstIdx++) {
            for (int secondIdx = firstIdx + 1; secondIdx < primes.size() - 1; secondIdx++) {
                int firstPrime = primes.get(firstIdx), secondPrime = primes.get(secondIdx);
                // should skip according to problem statement
                if (firstPrime == 1487 && secondPrime == 4817) {
                    continue;
                }
                int possibleThirdPrime = 2 * secondPrime - firstPrime;
                if (possibleThirdPrime >= LIMIT) {
                    break;
                }
                if (!primeSet.contains(possibleThirdPrime)) {
                    continue;
                }
                int[] firstDigits = Maths.getDigitFrequency(firstPrime);
                if (Arrays.equals(firstDigits, Maths.getDigitFrequency(secondPrime))
                        && Arrays.equals(firstDigits, Maths.getDigitFrequency(possibleThirdPrime))) {
                    return Integer.toString(firstPrime)
                            + Integer.toString(secondPrime)
                            + Integer.toString(possibleThirdPrime);
                }
            }
        }
        return null;
    }
}

