import com.google.common.collect.Sets;
import com.google.common.primitives.Longs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class S050 {

    private static final int LIMIT = 1000 * 1000;

    public static int solve() {
        ArrayList<Integer> primes = Maths.generatePrimesBelow(LIMIT);
        HashSet<Integer> primeSet = Sets.newHashSet(primes);
        long[] partialPrimeSums = Maths.getPartialSums(Longs.toArray(primes));
        int intervalSize = Arrays.binarySearch(partialPrimeSums, LIMIT - 1);
        if (intervalSize < 0) {
            intervalSize = -intervalSize - 2;
        }
        while (intervalSize > 0) {
            int lower = 0;
            long sum;
            while ((lower + intervalSize) < partialPrimeSums.length
                    && (sum = partialPrimeSums[lower + intervalSize] - partialPrimeSums[lower]) < LIMIT) {
                if (primeSet.contains((int) sum)) {
                    return (int) sum;
                }
                lower++;
            }
            intervalSize--;
        }
        return -1;
    }
}

