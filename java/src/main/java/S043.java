import com.google.common.math.LongMath;

public class S043 {

    public static long solve() {
        long sum = 0;
        int[] digits = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        while (Maths.incrementPermutation(digits)) {
            if (digits[0] == 0 || digits[3] % 2 != 0 || digits[5] % 5 != 0) {
                continue;
            }
            if (isPanSpecial(toNumber(digits))) {
                sum += toNumber(digits);
            }
        }
        return sum;
    }

    private static long toNumber(int[] digits) {
        long number = digits[0];
        for (int i = 1; i < digits.length; i++) {
            number = number * 10 + digits[i];
        }
        return number;
    }

    private static final int[] DIVISORS = new int[] {17, 13, 11, 7, 5, 3, 2};
    private static boolean isPanSpecial(long pandigital) {
        for (int i = 0; i < DIVISORS.length; i++) {
            long subDigits = pandigital / LongMath.pow(10, i) % 1000;
            if (subDigits % DIVISORS[i] != 0) {
                return false;
            }
        }
        return true;
    }
}

