import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class CustomEncryption {

    private static final int CAESAR_SHIFT = 5;
    private static final int ALPHABET_POWER = 26;

    public static void main(String[] args) {
        String input = "IDontKnow";
        System.out.println("Input text:" + input);
        input = caesarAlgorithm(input, CAESAR_SHIFT);
        System.out.println("After caesar algorithm:" + input);

        System.out.println("Output text" + input);
    }

    /**
     *
     * @param text message that needed to be encrypted
     * @param shift shift symbols on alphabet
     * @return encrypted message
     */
    private static String caesarAlgorithm(String text, Integer shift) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(text.chars().toArray())
                .map(item -> (item + shift) % ALPHABET_POWER)
                .mapToObj(item -> (char) item)
                .forEachOrdered(sb::append);
        return sb.toString();
    }
}
