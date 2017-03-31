public class S028 {

    public static int solve() {
        return 1 + diagSum(3, 2, 500)
                + diagSum(5, 4, 500)
                + diagSum(7, 6, 500)
                + diagSum(9, 8, 500);
    }

    private static int diagSum(int start, int skip, int length) {
        int val = start;
        int delta = skip;
        int sum = val;
        for (int i = 1; i < length; i++) {
            delta += 8;
            val += delta;
            sum += val;
        }
        return sum;
    }

}
