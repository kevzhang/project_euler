import com.google.common.math.IntMath;

public class S005 {

    private static final int LIMIT = 20;

    public static long solve() {
        int ans = 1;
        for (int factor = 2; factor <= LIMIT; factor++) {
            int deficient = factor / IntMath.gcd(ans, factor);
            ans *= deficient;
        }
        return ans;
    }
}
