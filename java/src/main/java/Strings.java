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

    public static String reverse(String s) {
        char[] reversed = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            reversed[i] = s.charAt(s.length() - 1 - i);
        }
        return new String(reversed);
    }
}
