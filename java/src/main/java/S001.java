public class S001 {
    public static long solve() {
        return multipleSum(3, 999) + multipleSum(5, 999) - multipleSum(15, 999);
    }

    private static long multipleSum(long multiple, long limit) {
        long count = limit / multiple;
        long max = count * multiple;
        return (max + multiple) * count / 2;
    }
}