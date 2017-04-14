import utils.Fraction;

public class S057 {

    private static final int LIMIT = 1000;

    public static int solve() {
        Fraction fraction = Fraction.ONE;
        int count = 0;
        for (int i = 0; i < LIMIT; i++) {
            fraction = next(fraction);
            if (Maths.numDigits(fraction.getNumerator()) > Maths.numDigits(fraction.getDenominator())) {
                count++;
            }
        }
        return count;
    }

    private static Fraction next(Fraction frac) {
        return frac.add(Fraction.ONE).inverse().add(Fraction.ONE);
    }
}
