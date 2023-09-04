public class StringToIntegerConverter {

    public static final int NO_DIGITS_RESULT = 0;

    public int myAtoi(String s) {
        int index = -1;
        for (int i = 0; i < s.length(); i++) {
            char item = s.charAt(i);
            if (!isAllowedChar(item)) {
                break;
            }

            if (isSign(item)) {
                if (i + 1 >= s.length() || !isDigit(s.charAt(i + 1))) {
                    break;
                }
            } else if (item >= '0' && item <= '9') {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return NO_DIGITS_RESULT;
        }

        try {
            int result = 0;
            for (int i = index; i < s.length(); i++) {
                char item = s.charAt(i);
                if (item >= '0' && item <= '9') {
                    result = Math.multiplyExact(result, 10);
                    result = Math.addExact(result, item - '0');
                } else {
                    break;
                }
            }

            if (isNegative(index, s)) {
                result *= -1;
            }
            return result;
        } catch (ArithmeticException e) {
            return isNegative(index, s) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
    }

    private boolean isNegative(int index, String s) {
        return index > 0 && s.charAt(index - 1) == '-';
    }

    private boolean isAllowedChar(char c) {
        return isDigit(c) || isSign(c) || c == ' ';
    }

    private boolean isSign(char c) {
        return c == '-' || c == '+';
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
