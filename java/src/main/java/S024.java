public class S024 {

    private static final int DIGITS = 10;
    private static final int INDEX = 1000 * 1000 - 1;

    public static String solve() {
        int remaining = INDEX;
        boolean[] digitTable = new boolean[DIGITS];
        String permutation = "";
        for (int i = 0; i < DIGITS; i++) {
            int bucketSize = factorial(DIGITS - i - 1);
            int bucketIdx = remaining / bucketSize;
            int idx = getIth(digitTable, bucketIdx);
            digitTable[idx] = true;
            permutation += idx;
            remaining -= bucketIdx * bucketSize;
        }
        return permutation;
    }

    // get ith 'false' index
    private static int getIth(boolean[] table, int i) {
        int count = 0;
        for (int j = 0; j < table.length; j++) {
            if (!table[j]) {
                count++;
            }
            if ((count - 1) == i) {
                return j;
            }
        }
        return -1;
    }

    private static int factorial(int n) {
        int prod = 1;
        for (int i = 2; i <= n; i++) {
            prod *= i;
        }
        return prod;
    }

}
