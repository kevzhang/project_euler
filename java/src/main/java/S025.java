import java.math.BigInteger;

public class S025 {

    private static final int DIGITS = 1000;

    public static int solve() {
        BigInteger left = BigInteger.ONE;
        BigInteger right = BigInteger.ONE;
        for (int i = 3;; i++) {
            BigInteger next = left.add(right);
            if (next.toString().length() == DIGITS) {
                return i;
            }
            left = right;
            right = next;
        }
    }

}
