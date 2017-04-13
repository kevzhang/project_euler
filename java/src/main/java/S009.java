import com.google.common.math.DoubleMath;

import java.math.RoundingMode;

public class S009 {

    private static final int SUM = 1000;

    public static long solve() {
        int smallestC = DoubleMath.roundToInt(SUM * (Math.sqrt(2) / (Math.sqrt(2) + 2)), RoundingMode.DOWN);
        for (int c = smallestC; c < SUM; c++) {
            int left = SUM - c;
            for (int a = 1; a < left; a++) {
                int b = left - a;
                if (a * a + b * b == c * c) {
                    return a * b * c;
                }
            }
        }
        return -1;
    }
}
