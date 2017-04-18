import com.google.common.math.IntMath;

public class S072 {

    private static final int LIMIT = Maths.MILLION;

    public static long solve() {
        long sum = 0;
        int[] totientTable = totientTable(LIMIT + 1);
        for (int denom = 2; denom <= LIMIT; denom++) {
            sum += totientTable[denom];
        }
        return sum;
    }

    private static int[] totientTable(int limit) {
        int[] totientTable = new int[limit];
        for (int i = 2; i < limit; i++) {
            if (totientTable[i] == 0) {
                for (int j = i; j < limit; j += i) {
                    int exp = 1;
                    int pow = i * i;
                    while (j % pow == 0) {
                        exp++;
                        pow *= i;
                    }
                    if (totientTable[j] == 0) {
                        totientTable[j] = IntMath.pow(i, exp - 1) * (i - 1);
                    } else {
                        totientTable[j] *= IntMath.pow(i, exp - 1) * (i - 1);
                    }
                }
            }
        }
        return totientTable;
    }
}
