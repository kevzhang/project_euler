import utils.Fraction;

import java.math.BigInteger;

public class S065 {

    private static final int TARGET = 33;

    public static int solve() {
        Fraction fraction = Fraction.ZERO;
        for (int i = TARGET; i >= 1; i--) {
            fraction = fraction.add(Fraction.ONE).inverse()
                    .add(Fraction.create(BigInteger.valueOf(2 * i))).inverse()
                    .add(Fraction.ONE).inverse();
        }
        fraction = fraction.add(Fraction.ONE).add(Fraction.ONE);
        return Maths.sum(Maths.getDigits(fraction.getNumerator()));
    }
}
