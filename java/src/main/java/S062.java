import com.google.common.collect.ArrayListMultimap;
import com.google.common.math.LongMath;

import java.util.Arrays;
import java.util.List;

public class S062 {

    private static final int TARGET = 5;
    private static final int LIMIT = 10 * 1000;

    public static long solve() {
        ArrayListMultimap<String, Long> permKeyToCube = ArrayListMultimap.create();
        for (long toCube = 1; toCube < LIMIT; toCube++) {
            long cube = LongMath.pow(toCube, 3);
            permKeyToCube.put(getPermutationKey(cube), cube);
        }
        for (long toCube = 1; toCube < LIMIT; toCube++) {
            long cube = LongMath.pow(toCube, 3);
            List<Long> cubes = permKeyToCube.get(getPermutationKey(cube));
            if (cubes.size() == TARGET) {
                return cubes.get(0);
            }
        }
        return -1;
    }

    private static String getPermutationKey(long n) {
        return Arrays.toString(Maths.getDigitFrequency(n));
    }
}
