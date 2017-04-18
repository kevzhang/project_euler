import com.google.common.math.IntMath;

public class S073 {

    private static final int LIMIT = 12 * 1000;

    public static long solve() {
        return inBetween(new int[]{1, 3}, new int[]{1, 2});
    }

    private static int inBetween(int[] left, int[] right) {
        int[] next = new int[]{left[0] + right[0], left[1] + right[1]};
        if (next[1] > LIMIT) {
            reduce(next);
        }
        if (next[1] <= LIMIT) {
            return 1 + inBetween(left, next) + inBetween(next, right);
        } else {
            return 0;
        }
    }

    private static void reduce(int[] frac) {
        int gcd = IntMath.gcd(frac[0], frac[1]);
        frac[0] /= gcd;
        frac[1] /= gcd;
    }
}
