public class S047 {

    private static final int LIMIT = 1000 * 1000;

    public static int solve() {
        int[] multiplicitySieve = new int[LIMIT];
        for (int i = 2; i < LIMIT; i++) {
            if (multiplicitySieve[i] == 0) {
                multiplicitySieve[i] = 1;
                for (int j = 2 * i; j < LIMIT; j += i) {
                    multiplicitySieve[j]++;
                }
            }
        }
        int consecutiveFours = 0;
        for (int search = 2; search < LIMIT; search++) {
            if (multiplicitySieve[search] == 4) {
                consecutiveFours++;
            } else {
                consecutiveFours = 0;
            }
            if (consecutiveFours == 4) {
                return search - 3;
            }
        }
        return -1;
    }
}

