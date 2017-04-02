import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.math.IntMath;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class S041 {

    // sum of digits (1, ..., n) for n = 8 and n = 9 produces a multiple of 3, so cap at
    private static final int DIGIT_LIMIT = 7;

    public static int solve() {
        for (int i = DIGIT_LIMIT; i > 0; i--) {
            Collection<List<Integer>> permutations = Collections2.orderedPermutations(
                    Lists.reverse(getDigits(i)),
                    Ordering.natural().reversed());
            for (List<Integer> digits : permutations) {
                if (IntMath.isPrime(getNumber(digits))) {
                    return getNumber(digits);
                }
            }
        }
        return -1;
    }

    private static List<Integer> getDigits(int n) {
        return IntStream.range(1, n + 1).boxed().collect(Collectors.toList());
    }

    private static int getNumber(List<Integer> digits) {
        Iterator<Integer> it = digits.iterator();
        int num = it.next();
        while (it.hasNext()) {
           num = num * 10 + it.next();
        }
        return num;
    }
}

