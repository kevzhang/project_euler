import java.util.Arrays;

public class S052 {

    public static int solve() {
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            if (isSpecial(i)) {
                return i;
            }
        }
        return -1;
    }

    private static boolean isSpecial(int n) {
        int[] digitFrequency = Maths.getDigitFrequency(n);
        for (int multiple = 2; multiple <= 6; multiple++) {
            if (!Arrays.equals(digitFrequency, Maths.getDigitFrequency(n * multiple))) {
                return false;
            }
        }
        return true;
    }

}

