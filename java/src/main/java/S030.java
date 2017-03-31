import com.google.common.math.IntMath;

import java.util.stream.IntStream;

public class S030 {

    private static final int MAX = 1000 * 1000;

    public static int solve() {
        return IntStream.range(2, MAX).filter(S030::isSumFifthPower).sum();
    }

    private static boolean isSumFifthPower(int n) {
        int remaining = n;
        int sum = 0;
        while (remaining > 0) {
            sum += IntMath.pow(remaining % 10, 5);
            remaining /= 10;
        }
        return sum == n;
    }

}
