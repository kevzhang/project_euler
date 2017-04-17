import com.google.common.math.IntMath;

import java.math.RoundingMode;
import java.util.stream.IntStream;

public class S064 {

    private static final int LIMIT = 10 * 1000;

    public static long solve() {
        return IntStream.range(1, LIMIT + 1).map(S064::period).filter(i -> i % 2 == 1).count();
    }

    private static int period(int n) {
        int intSqrt = IntMath.sqrt(n, RoundingMode.DOWN);
        if (intSqrt * intSqrt == n) {
            return 0;
        }
        int baseNum = 1;
        int baseDenom = -intSqrt;

        int num = baseNum;
        int denom = baseDenom;

        int cycle = 0;
        while (true) {
            int flippedDenom = n - IntMath.pow(denom, 2);
            flippedDenom /= IntMath.gcd(flippedDenom, num);
            int flippedNum = -denom;
            int whole = (intSqrt + flippedNum) / flippedDenom;
            flippedNum -= whole * flippedDenom;
            num = flippedDenom;
            denom = flippedNum;
            cycle++;
            if (num == baseNum && denom == baseDenom) {
                return cycle;
            }
        }
    }
}
