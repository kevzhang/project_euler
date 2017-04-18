import com.google.common.math.IntMath;

import java.math.BigInteger;
import java.math.RoundingMode;

public class S066 {

    private static final int MAX_D = 1000;

    public static int solve() {
        BigInteger maxMinX = BigInteger.ZERO;
        int maxMinXD = -1;
        for (int D = 1; D <= MAX_D; D++) {
            if (IntMath.pow(IntMath.sqrt(D, RoundingMode.DOWN), 2) == D) {
                continue;
            }
            BigInteger minX = minX(D);
            if (minX.compareTo(maxMinX) > 0) {
                maxMinX = minX;
                maxMinXD = D;
            }
        }
        return maxMinXD;
    }

    // Chakravala method
    private static BigInteger minX(int D) {
        BigInteger a = BigInteger.valueOf(IntMath.sqrt(D, RoundingMode.DOWN) + 1);
        BigInteger b = BigInteger.ONE;
        BigInteger bigD = BigInteger.valueOf(D);
        int k = a.multiply(a).intValue() - D;
        while (k != 1) {
            int m = nextM(a, b, k, D);
            BigInteger bigM = BigInteger.valueOf(m);
            BigInteger bigK = BigInteger.valueOf(k);
            BigInteger nextA = a.multiply(bigM).add(b.multiply(bigD)).divide(bigK);
            BigInteger nextB = a.add(b.multiply(bigM)).divide(bigK);
            BigInteger nextK = bigM.pow(2).subtract(bigD).divide(bigK);
            a = nextA;
            b = nextB;
            k = nextK.intValue();
        }
        return a.abs();
    }

    private static int nextM(BigInteger a, BigInteger b, int k, int D) {
        // a' = (am + Db) / k
        // b' = (a + bm) / k
        // k' = (m^2 - D) / k
        // want b' to be integer, |k'| to be minimized
        int sqrtD = IntMath.sqrt(D, RoundingMode.DOWN);
        int upSearch = sqrtD;
        int downSearch = sqrtD;
        BigInteger bigAbsK = BigInteger.valueOf(k).abs();
        BigInteger aModK = a.mod(bigAbsK);
        BigInteger bModK = b.mod(bigAbsK);
        while (!aModK.add(bModK.multiply(BigInteger.valueOf(upSearch))).mod(bigAbsK).equals(BigInteger.ZERO)) {
            upSearch++;
        }
        while (!aModK.add(bModK.multiply(BigInteger.valueOf(downSearch))).mod(bigAbsK).equals(BigInteger.ZERO)) {
            downSearch--;
        }
        int topDelta = Math.abs(upSearch * upSearch - D);
        int bottomDelta = Math.abs(downSearch * downSearch - D);
        if (topDelta < bottomDelta) {
            return upSearch;
        } else {
            return downSearch;
        }
    }
}
