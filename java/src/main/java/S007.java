public class S007 {

    private static final int LIMIT = 10001;

    public static long solve() {
        return Maths.generatePrimes(LIMIT).get(LIMIT - 1);
    }
}
