import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;

import java.util.stream.IntStream;

public class S074 {

    private static final int LIMIT = Maths.MILLION;
    private static final int TARGET = 60;

    public static long solve() {
        return IntStream.range(1, LIMIT).filter(i -> isGoodChain(i)).count();
    }

    private static boolean isGoodChain(int n) {
        int[] seen = new int[TARGET];
        int cur = n, count = 0;
        do {
            seen[count++] = cur;
            cur = advance(cur);
        } while (Ints.indexOf(seen, cur) < 0 && count < TARGET);
        return count == TARGET && Ints.indexOf(seen, cur) >= 0;
    }

    private static int advance(int n) {
        int remaining = n;
        int factorialDigitSum = 0;
        while (remaining > 0) {
            factorialDigitSum += IntMath.factorial(remaining % 10);
            remaining /= 10;
        }
        return factorialDigitSum;
    }
}
