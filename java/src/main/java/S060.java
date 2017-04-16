import com.google.common.math.IntMath;

import java.math.RoundingMode;
import java.util.ArrayList;

public class S060 {

    // @arbitrary
    private static final int LIMIT = 10 * 1000;
    private static final int GROUP_SIZE = 5;

    public static long solve() {
        ArrayList<Integer> primeList = Maths.generatePrimesBelow(LIMIT);
        int bestSum = Integer.MAX_VALUE;
        int[] candidates = new int[GROUP_SIZE];
        return search(bestSum, primeList, 0, candidates, 0);
    }

    private static int search(int sumLimit, ArrayList<Integer> primes, int startSearchIdx, int[] candidates, int size) {
        if (size == GROUP_SIZE) {
            return Maths.sum(candidates);
        }
        int bestSum = sumLimit;
        for (int searchIdx = startSearchIdx; searchIdx < primes.size(); searchIdx++) {
            int searchingPrime = primes.get(searchIdx).intValue();
            candidates[size] = searchingPrime;
            if (isTooHigh(bestSum, candidates, size + 1)) {
                break;
            }
            if (isLastItemValidWithRest(candidates, size + 1)) {
                bestSum = Math.min(bestSum, search(bestSum, primes, searchIdx + 1, candidates, size + 1));
            }
        }
        return bestSum;
    }

    private static boolean isTooHigh(int sumLimit, int[] candidates, int size) {
        int curSum = Maths.sum(candidates, size);
        return (GROUP_SIZE - size) * candidates[size - 1] + curSum > sumLimit;
    }

    private static boolean isLastItemValidWithRest(int[] candidates, int limit) {
        int last = candidates[limit - 1];
        for (int i = limit - 2; i >= 0; i--) {
            int compareTo = candidates[i];
            if (!IntMath.isPrime(concat(last, compareTo)) || !IntMath.isPrime(concat(compareTo, last))) {
                return false;
            }
        }
        return true;
    }

    private static int concat(int a, int b) {
        int bDigits = IntMath.log10(b, RoundingMode.DOWN) + 1;
        return a * IntMath.pow(10, bDigits) + b;
    }
}
