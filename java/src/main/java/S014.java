
public class S014 {
    private static final int LIMIT = 1000 * 1000;
    public static int solve() {
        int[] startLengthTable = new int[LIMIT];
        startLengthTable[1] = 1;
        for (int i = 2; i < LIMIT; i++) {
            collatzLengthMemoized(startLengthTable, i);
        }
        int maxLength = -1;
        int maxIdx = -1;
        for (int i = 1; i < LIMIT; i++) {
            if (startLengthTable[i] > maxLength) {
                maxLength = startLengthTable[i];
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    private static int collatzLengthMemoized(int[] dp, long curVal) {
        long nextVal;
        if (curVal % 2 == 0) {
            nextVal = curVal / 2;
        } else {
            nextVal = 3 * curVal + 1;
        }
        if (curVal >= dp.length) {
            return 1 + collatzLengthMemoized(dp, nextVal);
        } else if (dp[(int) curVal] == 0) {
            dp[(int) curVal] = 1 + collatzLengthMemoized(dp, nextVal);
            return dp[(int) curVal];
        } else {
            return dp[(int) curVal];
        }
    }
}
