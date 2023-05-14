import org.testng.annotations.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class StringToIntegerConverterTest {

    private static final StringToIntegerConverter CONVERTER = new StringToIntegerConverter();

    @Test
    public void simpleConvertPositive() {
        String s = "25";

        int result = CONVERTER.myAtoi(s);

        assertThat(result).isEqualTo(25);
    }

    @Test
    public void simpleConvertWithLeadingZero() {
        String s = "0032";

        int result = CONVERTER.myAtoi(s);

        assertThat(result).isEqualTo(32);
    }

    @Test
    public void simpleConvertNegative() {
        String s = "-38";

        int result = CONVERTER.myAtoi(s);

        assertThat(result).isEqualTo(-38);
    }

    @Test
    public void simpleConvertWithRubbish() {
        String s = "word -38 word1";

        int result = CONVERTER.myAtoi(s);

        assertThat(result).isEqualTo(0);
    }

    @Test
    public void convertLessThanMinValue() {
        String s = "-6147483648";

        int result = CONVERTER.myAtoi(s);

        assertThat(result).isEqualTo(-2147483648);
    }

    @Test
    public void convertWithMultipleSigns() {
        String s = "+-12";

        int result = CONVERTER.myAtoi(s);

        assertThat(result).isEqualTo(0);
    }

    @Test
    public void convertWithMultipleSignDividedByWhitespace() {
        String s = "+ -12";

        int result = CONVERTER.myAtoi(s);

        assertThat(result).isEqualTo(0);
    }

    @Test
    public void convertSimpleSign() {
        String s = "+";

        int result = CONVERTER.myAtoi(s);

        assertThat(result).isEqualTo(0);
    }
}
