import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.common.collect.Lists;

public class S021 {

    public static final int LIMIT = 10 * 1000;

    public static int solve() {
        return IntStream.range(2, LIMIT).filter(S021::isAmicable).sum();
    }

    private static boolean isAmicable(int n) {
        int partner = sumProperDivisors(n);
        return partner != n && sumProperDivisors(partner) == n;
    }

    private static int sumProperDivisors(int n) {
        return Maths.getDivisors(n).stream().mapToInt(Long::intValue).sum() - n;
    }

}
