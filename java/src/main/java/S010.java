public class S010 {

    private static final int LIMIT = 2 * 1000 * 1000;

    public static long solve() {
        return Maths.generatePrimesBelow(LIMIT).stream()
            .mapToLong(i -> (long) i).sum();
    }
}
