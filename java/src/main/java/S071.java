import com.google.common.math.IntMath;

public class S071 {

    private static final int LIMIT = Maths.MILLION;

    public static long solve() {
        int[] leftFrac = new int[]{2, 5};
        int[] rightFrac = new int[]{3, 7};
        int[] nextFrac;
        while ((nextFrac = next(leftFrac, rightFrac))[1] <= LIMIT) {
            leftFrac = nextFrac;
        }
        return leftFrac[0];
    }

    private static int[] next(int[] left, int[] right) {
        int[] next = new int[]{left[0] + right[0], left[1] + right[1]};
        int gcd = IntMath.gcd(next[0], next[1]);
        next[0] /= gcd;
        next[1] /= gcd;
        return next;
    }

}
