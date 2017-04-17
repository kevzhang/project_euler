package utils;

import com.google.common.base.Preconditions;

import java.math.BigInteger;

public class Fraction {

    private final BigInteger numerator;
    private final BigInteger denominator;

    public static final Fraction ONE = create(BigInteger.ONE);
    public static final Fraction ZERO = create(BigInteger.ZERO);

    private Fraction(BigInteger[] params) {
        this.numerator = params[0];
        this.denominator = params[1];
    }

    private Fraction(BigInteger numerator, BigInteger denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public static Fraction create(BigInteger numerator, BigInteger denominator) {
        Preconditions.checkArgument(!denominator.equals(BigInteger.ZERO), "Dividing by 0 in fraction.");
        return new Fraction(simplify(numerator, denominator));
    }

    public static Fraction create(BigInteger value) {
        return create(value, BigInteger.ONE);
    }

    public Fraction inverse() {
        return new Fraction(denominator, numerator);
    }

    // Efficiently adds one
    private Fraction addOne() {
        // numerator and denominator are already relatively prime
        return new Fraction(numerator.add(denominator), denominator);
    }

    public Fraction add(Fraction frac) {
        if (frac.equals(ONE)) {
            return addOne();
        }
        BigInteger newNumerator = this.numerator.multiply(frac.denominator).add(frac.numerator.multiply(this.denominator));
        BigInteger newDenominator = this.denominator.multiply(frac.denominator);
        return create(newNumerator, newDenominator);
    }

    public Fraction multiply(Fraction frac) {
        BigInteger newNumerator = this.numerator.multiply(frac.numerator);
        BigInteger newDenominator = this.denominator.multiply(frac.denominator);
        return create(newNumerator, newDenominator);
    }

    public BigInteger getNumerator() {
        return numerator;
    }

    public BigInteger getDenominator() {
        return denominator;
    }

    private static BigInteger[] simplify(BigInteger numerator, BigInteger denominator) {
        BigInteger gcd = numerator.gcd(denominator);
        return new BigInteger[]{numerator.divide(gcd), denominator.divide(gcd)};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fraction fraction = (Fraction) o;

        if (numerator != null ? !numerator.equals(fraction.numerator) : fraction.numerator != null) return false;
        return denominator != null ? denominator.equals(fraction.denominator) : fraction.denominator == null;
    }

    @Override
    public int hashCode() {
        int result = numerator != null ? numerator.hashCode() : 0;
        result = 31 * result + (denominator != null ? denominator.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Fraction{" +
                "numerator=" + numerator +
                ", denominator=" + denominator +
                '}';
    }
}
