import java.math.BigInteger;

public class S056 {

    private static final int LIMIT = 100;

    public static int solve() {
        int maxDigitSum = 0;

        // do not consider single digit values for a
        for (int a = 11; a < LIMIT; a++) {
            // probabilistically speaking, the max should be  200 * (5) ~ 1000
            // This is impossible if we have less than 100 digits, so consider b >= 50
            for (int b = 50; b < LIMIT; b++) {
                maxDigitSum = Math.max(maxDigitSum, digitSum(BigInteger.valueOf(a).pow(b)));
            }
        }
        return maxDigitSum;
    }

    private static int digitSum(BigInteger n) {
        String str = n.toString();
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            sum += str.charAt(i) - '0';
        }
        return sum;
    }
}
