public class S004 {

    public static long solve() {
        for (int skip = 0; skip < 999; skip++) {
            int high = 999;
            int low = 999 - skip;
            while (high >= low) {
                if (isPalindrome(high * low)) {
                    return high * low;
                }
                high--;
                low++;
            }
        }
        return -1;
    }

    private static boolean isPalindrome(int n) {
        StringBuilder string = new StringBuilder(Long.toString(n));
        return string.toString().equals(string.reverse().toString());
    }
}
