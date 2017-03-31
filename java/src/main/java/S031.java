public class S031 {

    private static final int[] DENOMS = new int[] {1, 2, 5, 10, 20, 50, 100, 200};
    private static final int TARGET = 200;

    public static int solve() {
        int[][] dp = new int[DENOMS.length + 1][TARGET + 1];
        // column 0 => make 0p => exactly 1 way to do this
        for (int r = 0; r <= DENOMS.length; r++) {
            dp[r][0] = 1;
        }
        // making >= 1p with no coins is impossible
        for (int c = 1; c <= TARGET; c++) {
            // extraneous, visual purposes
            dp[0][c] = 0;
        }
        for (int denomIdx = 1; denomIdx <= DENOMS.length; denomIdx++) {
            for (int curTarget = 1; curTarget <= TARGET; curTarget++) {
                int curDenom = DENOMS[denomIdx - 1];
                if (curDenom > curTarget) {
                    dp[denomIdx][curTarget] = dp[denomIdx - 1][curTarget];
                } else {
                    int ways = dp[denomIdx - 1][curTarget] + dp[denomIdx][curTarget - curDenom];
                    dp[denomIdx][curTarget] = ways;
                }
            }
        }
        return dp[DENOMS.length][TARGET];
    }

}
