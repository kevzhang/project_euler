public class S003 {

    private static final long NUMBER = 600851475143L;

    public static long solve() {
        long cur = NUMBER;
        long factor = 1;
        while (cur > 1) {
            factor++;
            while (cur % factor == 0) {
                cur /= factor;
            }
        }
        return factor;
    }
}
