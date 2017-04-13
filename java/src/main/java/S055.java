import java.math.BigInteger;
import java.util.stream.IntStream;

public class S055 {

    private static final int LIMIT = 10 * 1000;
    private static final int ITERATION_LIMIT = 49;

    public static int solve() {
        return (int) IntStream.range(1, LIMIT).filter(S055::isLychrel).count();
    }

    private static boolean isLychrel(int n) {
        BigInteger cur = BigInteger.valueOf(n);
        for (int i = 0; i < ITERATION_LIMIT; i++) {
            cur = cur.add(reverseValue(cur));
            if (Strings.isPalindrome(cur.toString())) {
                return false;
            }
        }
        return true;
    }

    private static BigInteger reverseValue(BigInteger n) {
        return new BigInteger(Strings.reverse(n.toString()));
    }
}
