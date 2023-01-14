public class Palindrome {

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
