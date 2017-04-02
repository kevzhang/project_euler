import com.google.common.collect.Lists;
import com.google.common.math.IntMath;

import java.math.RoundingMode;
import java.util.List;

public class S038 {

    public static int solve() {
        List<Integer> candidates = Lists.newArrayList();
        // only consider base numbers of up to 4 digits, and starts with 9
        for (int pow = 1; pow <= 3; pow++) {
            int lowerRange = IntMath.pow(10, pow) * 9;
            int upperRange = IntMath.pow(10, pow + 1);
            for (int i = lowerRange; i < upperRange; i++) {
                candidates.add(tryPanMultiple(i));
            }
        }
        return candidates.stream().mapToInt(Integer::intValue).max().getAsInt();
    }

    private static int tryPanMultiple(int base) {
        boolean[] usedDigits = new boolean[9];
        int panDigital = 0, usedDigitCount = 0, multiplier = 1;
        while (usedDigitCount < 9) {
            int remaining = base * multiplier;
            int numDigits = IntMath.log10(remaining, RoundingMode.DOWN) + 1;
            usedDigitCount += numDigits;
            while (remaining > 0) {
                int digit = remaining % 10;
                if (digit == 0 || usedDigits[digit - 1]) {
                    return -1;
                }
                usedDigits[digit - 1] = true;
                remaining /= 10;
            }
            panDigital = panDigital * IntMath.pow(10, numDigits) + base * multiplier;
            multiplier++;
        }
        return usedDigitCount == 9 ? panDigital : -1;
   }
}
