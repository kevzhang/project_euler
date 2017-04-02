import com.google.common.math.IntMath;

import java.math.RoundingMode;

public class S040 {

    public static int solve() {
        int product = 1;
        for (int pow = 0; pow <= 6; pow++) {
            product *= getD(IntMath.pow(10, pow));
        }
        return product;
    }

    private static int getD(int n) {
        int prevIdx = 1, prevNum = 1;
        int nextIdx = 10, nextNum = 10;
        while (n >= nextIdx) {
            prevIdx = nextIdx;
            prevNum = nextNum;
            int nextNumDigits = IntMath.log10(nextNum, RoundingMode.DOWN) + 1;
            nextIdx += nextNumDigits * nextNum * 9;
            nextNum *= 10;
        }
        int numDigits = IntMath.log10(prevNum, RoundingMode.DOWN) + 1;
        int deltaFromPrev = n - prevIdx;
        int targetNum = prevNum + (deltaFromPrev / numDigits);
        int digit = deltaFromPrev % numDigits;
        return Integer.toString(targetNum).charAt(digit) - '0';
    }
}

