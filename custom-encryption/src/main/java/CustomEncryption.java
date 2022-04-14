import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CustomEncryption {

    private static final int CAESAR_SHIFT = 5;
    private static final int ALPHABET_POWER = 32;
    private static final String INPUT_PATTERN = "[А-Яа-я\\.\\;\\s]";
    private static final String KEY = "212a180a-fd75-4196-b319-77e7e99365d2";

    public static void main(String[] args) {
        String input = "Знать прошлое достаточно неприятно; знать еще и будущее было бы просто невыносимо.";
        assert Pattern.matches(INPUT_PATTERN, input) :
                String.format("Input data must matches pattern below: %n %s", INPUT_PATTERN);
        System.out.println("Input text: " + input);
        input = Encryptor.caesarAlgorithm(input, CAESAR_SHIFT);
        System.out.println("After caesar algorithm: " + input);
        input = Encryptor.summation(input);
        System.out.println("After summation: ");
        System.out.print(input);
    }

    /**
     * Class for encryption methods
     */
    private static class Encryptor {

        /**
         * @param text message that needed to be encrypted
         * @param shift shift symbols on alphabet
         * @return encrypted message
         */
        public static String caesarAlgorithm(String text, Integer shift) {
            StringBuilder sb = new StringBuilder();

            Arrays.stream(text.chars().toArray())
                    .map(item -> {
                        if(item >= 'А' && item <= 'я') { //[А-Яа-я]
                            int result = item + shift;
                            if(result > 'я') {
                                result -= (ALPHABET_POWER * 2);
                            }
                            return result;
                        }
                        return item;
                    })
                    .mapToObj(item -> (char) item)
                    .forEachOrdered(sb::append);
            return sb.toString();
        }

        /**
         * @apiNote XOR, гаммирование
         * @param text message that needed to be encrypted
         * @return encrypted message
         */
        public static String summation(String text) {
            List<Character> result = new ArrayList<>(text.length());
            for(int i=0; i < text.length(); i++) {
                int symbol = (text.charAt(i) ^ KEY.charAt(i % KEY.length()));
                result.add((char) symbol);
            }
            StringBuilder sb = new StringBuilder();
            result.stream().forEachOrdered(sb::append);
            return sb.toString();
        }

    }
}
