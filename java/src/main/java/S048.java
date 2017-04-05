import com.google.common.math.LongMath;

public class S048 {

    private static final int LIMIT = 1000;
    private static final long MOD = LongMath.pow(10, 10);

    public static long solve() {
        long sum = 0;
        for (int pow = 1; pow <= LIMIT; pow++) {
            sum += modPow(pow);
        }
        return sum % LongMath.pow(10, 10);
    }

    private static long modPow(int pow) {
        long res = 1;
        for (int i = 0; i < pow; i++) {
            res  = res * pow % MOD;
        }
        return res;
    }
}

