import com.google.common.math.IntMath;

import java.math.RoundingMode;

public class S046 {

    public static int solve() {
        for (int n = 3;; n += 2) {
            if (IntMath.isPrime(n)) {
                continue;
            }
            boolean found = false;
            for (int prime = n - 2; prime >= 2; prime--) {
                if (!IntMath.isPrime(prime)) {
                    continue;
                }
                int remaining = n - prime;
                if (remaining % 2 != 0) {
                    continue;
                }
                if (isSquare(remaining / 2)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return n;
            }
        }
    }

    private static boolean isSquare(int n) {
        return IntMath.pow(IntMath.sqrt(n, RoundingMode.DOWN), 2) == n;
    }
}

