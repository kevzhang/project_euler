import java.util.stream.IntStream;

public class S036 {

    private static final int LIMIT = 1000 * 1000;

    public static int solve() {
        return IntStream.range(1, LIMIT).filter(S036::isPalindromeInBaseTenAndTwo).sum();
    }

    private static boolean isPalindromeInBaseTenAndTwo(int n) {
        return Strings.isPalindrome(Integer.toString(n)) && Strings.isPalindrome(Integer.toBinaryString(n));
    }
}
