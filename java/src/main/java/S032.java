import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.math.IntMath;

import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class S032 {

    private static final int MIN = 1000;
    private static final int MAX = 10 * 1000;

    public static int solve() {
        return IntStream.range(MIN, MAX).filter(S032::isSpecial).sum();
    }

    private static boolean isSpecial(int n) {
        Set<Integer> productDigits = getDigits(n);
        if (productDigits.size() < numDigits(n) || productDigits.contains(0)) {
            return false;
        }
        Set<Integer> leftDigits = Sets.newHashSet();
        for (int i = 1; i < 10; i++) {
            if (!productDigits.contains(i)) {
                leftDigits.add(i);
            }
        }
        Collection<List<Integer>> permutations = Collections2.permutations(leftDigits);
        for (List<Integer> digits : permutations) {
            for (Integer product : getPossibleProducts(digits)) {
                if (product == n) {
                    return true;
                }
            }
        }
        return false;
    }

    private static List<Integer> getPossibleProducts(List<Integer> digits) {
        List<Integer> products = Lists.newArrayList();
        for (int split = 1; split < digits.size(); split++) {
            List<Integer> leftDigits = digits.subList(0, split);
            List<Integer> rightDigits = digits.subList(split, digits.size());
            products.add(toNumber(leftDigits) * toNumber(rightDigits));
        }
        return products;
    }

    private static int toNumber(List<Integer> digits) {
        int number = digits.get(0);
        for (int i = 1; i < digits.size(); i++) {
            number *= 10;
            number += digits.get(i);
        }
        return number;
    }

    private static int numDigits(int n) {
        return IntMath.log10(n, RoundingMode.DOWN) + 1;
    }

    private static Set<Integer> getDigits(int n) {
        int remaining = n;
        Set<Integer> digits = Sets.newHashSet();
        while (remaining > 0) {
            digits.add(remaining % 10);
            remaining /= 10;
        }
        return digits;
    }

}
