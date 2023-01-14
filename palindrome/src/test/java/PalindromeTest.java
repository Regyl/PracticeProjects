import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PalindromeTest {

    @Test
    public void shouldWorksWithNull() {
        String input = null;

        boolean result = Palindrome.isPalindrome(input);

        assertThat(result).isFalse();
    }

    @Test
    public void shouldWorksWithEmpty() {
        String input = "";

        boolean result = Palindrome.isPalindrome(input);

        assertThat(result).isTrue();
    }

    @Test
    public void shouldWorksWithOne() {
        String input = "h";

        boolean result = Palindrome.isPalindrome(input);

        assertThat(result).isTrue();
    }

    @Test
    public void shouldWorksWithEvenLength() {
        String input = "19233291";

        boolean result = Palindrome.isPalindrome(input);

        assertThat(result).isTrue();
    }

    @Test
    public void shouldWorksWithOddLength() {
        String input = "1923 5 3291";

        boolean result = Palindrome.isPalindrome(input);

        assertThat(result).isTrue();
    }

    @Test
    public void negativeTestCase() {
        String input = "1923 5 1923";

        boolean result = Palindrome.isPalindrome(input);

        assertThat(result).isFalse();
    }

    @Test
    public void secondNegativeTestCase() {
        String input = "19233292";

        boolean result = Palindrome.isPalindrome(input);

        assertThat(result).isFalse();
    }

    @Test
    public void thirdNegativeTestCase() {
        String input = "29233291";

        boolean result = Palindrome.isPalindrome(input);

        assertThat(result).isFalse();
    }

}
