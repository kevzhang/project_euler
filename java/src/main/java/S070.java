import com.google.common.math.IntMath;

import java.util.Arrays;

public class S070 {

    private static final int LIMIT = 10 * Maths.MILLION;

    public static long solve() {
        int[] totientTable = totientTable(LIMIT);
        double minRatio = Double.MAX_VALUE;
        int bestN = -1;
        for (int i = 2; i < LIMIT; i++) {
            double curRatio = (double) i / totientTable[i];
            if (curRatio < minRatio && isPerm(i, totientTable[i])) {
                minRatio = curRatio;
                bestN = i;
            }
        }
        return bestN;
    }

    private static int[] totientTable(int limit) {
        int[] totientTable = new int[limit];
        for (int i = 2; i < limit; i++) {
            if (totientTable[i] == 0) {
                for (int j = i; j < limit; j += i) {
                    int exp = 1;
                    int pow = i * i;
                    while (j % pow == 0) {
                        exp++;
                        pow *= i;
                    }
                    if (totientTable[j] == 0) {
                        totientTable[j] = IntMath.pow(i, exp - 1) * (i - 1);
                    } else {
                        totientTable[j] *= IntMath.pow(i, exp - 1) * (i - 1);
                    }
                }
            }
        }
        return totientTable;
    }

    private static boolean isPerm(long a, long b) {
        return Arrays.equals(Maths.getDigitFrequency(a), Maths.getDigitFrequency(b));
    }
}
