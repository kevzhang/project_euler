import com.google.common.collect.Lists;
import com.google.common.math.IntMath;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class S023 {

    private static final int LIMIT = 28123;

    public static int solve() {
        ArrayList<Integer> abundants = getAbundantNumbers(LIMIT);
        return IntStream.range(1, LIMIT).filter(i -> !canSum(abundants, i)).sum();
    }

    private static boolean canSum(ArrayList<Integer> sortedValues, int target) {
        int lower = 0, upper = sortedValues.size() - 1;
        while (lower <= upper) {
            int curSum = sortedValues.get(lower) + sortedValues.get(upper);
            if (curSum == target) {
                return true;
            } else if (curSum < target) {
                lower++;
            } else {
                upper--;
            }
        }
        return false;
    }

    private static int properDivisorSum(int n) {
        int sum = 0;
        int sqrtFloor = IntMath.sqrt(n, RoundingMode.DOWN);
        for (int i = 2; i < sqrtFloor; i++) {
            if (n % i == 0) {
                sum += i + (n / i);
            }
        }
        if (sqrtFloor > 1 && n % sqrtFloor == 0) {
            if (sqrtFloor * sqrtFloor == n) {
                sum += sqrtFloor;
            } else {
                sum += sqrtFloor + (n / sqrtFloor);
            }
        }
        return 1 + sum; // add "1" as a factor
    }

    private static ArrayList<Integer> getAbundantNumbers(int limit) {
        ArrayList<Integer> abundants = Lists.newArrayList();
        for (int i = 2; i < limit; i++) {
            if (properDivisorSum(i) > i) {
                abundants.add(i);
            }
        }
        return abundants;
    }
}
