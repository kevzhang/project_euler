import com.google.common.math.IntMath;

public class S006 {

    private static final int LIMIT = 100;

    public static long solve() {
        int sumOfSquares = (LIMIT * (LIMIT + 1) * (2 * LIMIT + 1)) / 6;
        int squareOfSums = IntMath.pow((1 + LIMIT) * LIMIT / 2, 2);
        return squareOfSums - sumOfSquares;
    }
}
