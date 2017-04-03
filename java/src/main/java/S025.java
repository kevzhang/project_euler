import com.google.common.math.BigIntegerMath;

import java.math.BigInteger;
import java.math.RoundingMode;

public class S025 {

    private static final int DIGITS = 1000;

    public static int solve() {
        BigInteger left = BigInteger.ONE;
        BigInteger right = BigInteger.ONE;
        for (int i = 3;; i++) {
            BigInteger next = left.add(right);
            int digits = BigIntegerMath.log10(next, RoundingMode.DOWN) + 1;
            if (digits == DIGITS) {
                return i;
            }
            left = right;
            right = next;
        }
    }

}
