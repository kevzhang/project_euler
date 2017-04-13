import com.google.common.collect.Lists;

import java.math.BigInteger;

public class S020 {

    private static final int N = 100;
    public static int solve() {
        BigInteger fact = factorial(N);
        return Lists.charactersOf(fact.toString()).stream()
            .mapToInt(c -> (c - '0')).sum();
    }

    private static BigInteger factorial(int n) {
        BigInteger fact = BigInteger.valueOf(n);
        for (int i = n - 1; i > 1; i--) {
            fact = fact.multiply(BigInteger.valueOf(i));
        }
        return fact;
    }
}
