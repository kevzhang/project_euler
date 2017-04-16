import com.google.common.math.IntMath;

import java.math.RoundingMode;
import java.util.ArrayList;

public class S060 {

    // @arbitrary
    private static final int LIMIT = 10 * 1000;
    private static final int GROUP_SIZE = 5;

    public static long solve() {
        ArrayList<Integer> primeList = Maths.generatePrimesBelow(LIMIT);
        // 1 -> true, 0 -> n/a, -1 -> false
        byte[][] concatTable = new byte[primeList.size()][primeList.size()];
        int bestSum = Integer.MAX_VALUE;
        int[] candidateIdx = new int[GROUP_SIZE], candidateValues = new int[GROUP_SIZE];
        return search(bestSum, concatTable, primeList, 0, candidateIdx, candidateValues, 0);
    }

    private static int search(int sumLimit, byte[][] concatTable, ArrayList<Integer> primes, int startSearchIdx,
                              int[] candidateIdx, int[] candidateValues, int size) {
        if (size == GROUP_SIZE) {
            return Maths.sum(candidateValues);
        }
        int bestSum = sumLimit;
        for (int searchIdx = startSearchIdx; searchIdx < primes.size(); searchIdx++) {
            candidateIdx[size] = searchIdx;
            candidateValues[size] = primes.get(searchIdx);
            if (isTooHigh(bestSum, candidateValues, size + 1)) {
                break;
            }
            if (isLastItemValidWithRest(primes, concatTable, candidateIdx, size + 1)) {
                bestSum = Math.min(bestSum, search(bestSum, concatTable, primes, searchIdx + 1,
                        candidateIdx, candidateValues, size + 1));
            }
        }
        return bestSum;
    }

    private static boolean isTooHigh(int sumLimit, int[] candidates, int size) {
        int curSum = Maths.sum(candidates, size);
        return (GROUP_SIZE - size) * candidates[size - 1] + curSum > sumLimit;
    }

    private static boolean isLastItemValidWithRest(ArrayList<Integer> primes, byte[][] concatTable,
                                                   int[] candidateIdx, int limit) {
        int last = candidateIdx[limit - 1];
        for (int i = limit - 2; i >= 0; i--) {
            int compareTo = candidateIdx[i];
            if (!concatsToPrimeCached(concatTable, primes, compareTo, last)
                    || !concatsToPrimeCached(concatTable, primes, last, compareTo)) {
                return false;
            }
        }
        return true;
    }

    private static boolean concatsToPrimeCached(byte[][] table, ArrayList<Integer> primes, int leftIdx, int rightIdx) {
        byte cached = table[leftIdx][rightIdx];
        if (cached == 0) {
            boolean result = IntMath.isPrime(concat(primes.get(leftIdx), primes.get(rightIdx)));
            table[leftIdx][rightIdx] = result ? (byte) 1 : -1;
            return result;
        } else {
            return cached == (byte) 1;
        }
    }

    private static int concat(int a, int b) {
        int bDigits = IntMath.log10(b, RoundingMode.DOWN) + 1;
        return a * IntMath.pow(10, bDigits) + b;
    }
}
