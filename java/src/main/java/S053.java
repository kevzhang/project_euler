import com.google.common.math.IntMath;

public class S053 {

    public static int solve() {
        int count = 0;
        for (int n = 100; n > 1; n--) {
            int specialRs = numSpecialRs(n);
            if (specialRs == 0) {
                break;
            } else {
                count += specialRs;
            }
        }
        return count;
    }

    private static int numSpecialRs(int n) {
        int half = n / 2;
        int count = 0;
        for (int r = half; r > 1; r--) {
            if (IntMath.binomial(n, r) <= Maths.MILLION) {
                break;
            } else {
                count++;
            }
        }
        if (count == 0) {
            return 0;
        }
        if (n - half == half) {
            return count * 2 - 1;
        } else {
            return count * 2;
        }
    }
}
