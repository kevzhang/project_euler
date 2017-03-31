import com.google.common.math.IntMath;

import java.util.stream.IntStream;

public class S034 {

    // greater than 9! * 7
    private static final int LIMIT = 3 * 1000 * 1000;

    public static int solve() {
        return IntStream.range(10, LIMIT).filter(S034::isCurious).sum();
    }

    private static boolean isCurious(int n) {
        int remaining = n;
        int factorialSum = 0;
        while (remaining > 0) {
            factorialSum += IntMath.factorial(remaining % 10);
            remaining /= 10;
        }
        return factorialSum == n;
    }
}
