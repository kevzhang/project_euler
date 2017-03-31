import com.google.common.collect.Sets;
import com.google.common.math.IntMath;

import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

public class S035 {

    private static final int LIMIT = 1000 * 1000;

    public static int solve() {
        HashSet<Integer> primes = Sets.newHashSet(Maths.generatePrimesBelow(LIMIT));
        return (int) primes.stream().filter(prime -> isCircular(primes, prime)).mapToInt(Integer::intValue).count();
    }

    private static boolean isCircular(Set<Integer> primes, int prime) {
        int digits = IntMath.log10(prime, RoundingMode.DOWN) + 1;
        int greatestDigit = IntMath.pow(10, digits - 1);
        int shifted = prime;
        for (int i = 0; i < digits - 1; i++) {
            int digit = shifted % 10;
            shifted /= 10;
            shifted += digit * greatestDigit;
            if (!primes.contains(shifted)) {
                return false;
            }
        }
        return true;
    }
}
