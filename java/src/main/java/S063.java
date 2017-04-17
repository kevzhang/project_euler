import java.math.BigInteger;

public class S063 {

    public static int solve() {
        // include numbers in range [1, 9]
        int count = 9;
        expSearch: for (int exp = 2;; exp++) {
            for (int base = 9; base >= 1; base--) {
                BigInteger bPow = BigInteger.valueOf(base).pow(exp);
                // num digits cannot be greater than exp
                int nDigits = Maths.numDigits(bPow);
                if (nDigits < exp) {
                    if (base == 9) {
                        break expSearch;
                    } else {
                        break;
                    }
                } else {
                    count++;
                }
            }
        }
        return count;
    }
}
