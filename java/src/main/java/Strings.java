public class Strings {

    public static boolean isPalindrome(String s) {
        int lower = 0;
        int upper = s.length() - 1;
        while (lower < upper) {
           if (s.charAt(lower) != s.charAt(upper)) {
               return false;
           }
           lower++;
           upper--;
        }
        return true;
    }
}
