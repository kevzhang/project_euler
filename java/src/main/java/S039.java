import com.google.common.math.DoubleMath;

import java.math.RoundingMode;
import java.util.stream.IntStream;

public class S039 {

    private static final int LIMIT = 1000;

    public static int solve() {
        int maxP = -1, maxSolutions = -1;
        for (int p = 1; p <= LIMIT; p++) {
            int nSolutions = numSolutionsFor(p);
            if (nSolutions > maxSolutions) {
                maxSolutions = nSolutions;
                maxP = p;
            }
        }
        return maxP;
    }

    // double has enough precision to intermingle with ints
    private static int numSolutionsFor(int p) {
        double lowerCRatio = Math.sqrt(2) / (Math.sqrt(2) + 2);
        double upperCRatio = 0.5;
        int lowerC = DoubleMath.roundToInt(lowerCRatio * p, RoundingMode.DOWN);
        int upperC = DoubleMath.roundToInt(upperCRatio * p, RoundingMode.DOWN);
        return IntStream.range(lowerC, upperC + 1)
                .filter(c -> hasSolution(p, c))
                .sum();
    }

    private static boolean hasSolution(int p, int c) {
        int sqrtTerm = c * c + 2 * c * p - p * p;
        if (sqrtTerm < 0) {
            return false;
        }
        int a = DoubleMath.roundToInt((Math.sqrt(sqrtTerm) - c + p) * 0.5, RoundingMode.HALF_DOWN);
        int b = p - c - a;
        return a * a + b * b == c * c;
    }
}

