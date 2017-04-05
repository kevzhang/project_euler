import com.google.common.math.DoubleMath;

import java.math.RoundingMode;

public class S044 {

    public static long solve() {
        long minDelta = Long.MAX_VALUE;
        for (int top = 2;; top++) {
            for (int bottom = top - 1; bottom >= 1; bottom--) {
                long pM = pentagonal(top);
                long pN = pentagonal(bottom);
                if (pM - pN >= minDelta) {
                    break;
                }
                if (isSpecial(pN, pM)) {
                    minDelta = Math.min(minDelta, pM - pN);
                }
            }
            if (pentagonal(top + 1) - pentagonal(top) > minDelta) {
                return minDelta;
            }
        }
    }

    // m > n
    private static boolean isSpecial(long pN, long pM) {
        return isPentagonal(pM - pN) && isPentagonal(pM + pN);
    }

    private static boolean isPentagonal(long p) {
        long candidate = DoubleMath.roundToLong((1 + Math.sqrt(1 + 24 * p)) / 6, RoundingMode.DOWN);
        return pentagonal(candidate) == p;
    }

    private static long pentagonal(long d) {
        return d * (3 * d - 1) / 2;
    }
}

