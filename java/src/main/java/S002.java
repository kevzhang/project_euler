public class S002 {

    private static final int LIMIT = 4 * 1000 * 1000;

    public static long solve() {
        long sum = 0;
        long prev = 1, cur = 2;
        while (cur < LIMIT) {
            if (cur % 2 == 0) {
                sum += cur;
            }
            long next = prev + cur;
            prev = cur;
            cur = next;
        }
        return sum;
    }
}
