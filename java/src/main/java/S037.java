import com.google.common.collect.Sets;
import com.google.common.math.IntMath;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class S037 {

    private static final int TARGET = 11;

    public static int solve() {
        ArrayList<Integer> primes = Maths.generatePrimesBelow(1000 * 1000);
        HashSet<Integer> primeSet = Sets.newHashSet(primes);
        int sum = 0;
        int count = 0;
        for (int i = 0; i < primes.size(); i++) {
            if (isLeftRightTruncatablePrime(primeSet, primes.get(i))) {
                sum += primes.get(i);
                count++;
            }
            if (count == 11) {
                return sum;
            }
        }
        throw new RuntimeException("Failed to fined 11 special primes.");
    }

    private static boolean isLeftRightTruncatablePrime(Set<Integer> primes, int prime) {
        if (hasZeros(prime) || prime < 10) {
            return false;
        }
        int remaining = prime / 10;
        while (remaining > 0) {
            if (!primes.contains(remaining)) {
                return false;
            }
            remaining /= 10;
        }
        int highestDigit = IntMath.pow(10, IntMath.log10(prime, RoundingMode.DOWN));
        remaining = prime % highestDigit;
        while (remaining > 0) {
            if (!primes.contains(remaining)) {
                return false;
            }
            highestDigit /= 10;
            remaining %= highestDigit;
        }
        return true;
    };

    private static boolean hasZeros(int n) {
        return Integer.toString(n).indexOf('0') >= 0;
    }

}
