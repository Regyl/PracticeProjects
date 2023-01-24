/**
 * Just a class with utility method.
 * @implNote This realization has O(n/2) (or to simplify O(n)) time complexity.
 */
public class Palindrome {

    /**
     * Checks if input string is palindrome.
     *
     * @param word input string
     * @return true if input string is palindrome, false otherwise
     */
    public static boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }

        int length = word.length();
        if (length == 0 || length == 1) {
            return Boolean.TRUE;
        }

        int stepCount = word.length() / 2;
        boolean flag;
        for (int i = 0; i < stepCount; i++) {
            flag = word.charAt(i) == word.charAt(length-1-i);
            if (!flag) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }
}
