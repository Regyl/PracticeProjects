package utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Standard input/output utils.
 */
public final class InOutMockUtils {

    /**
     * Mock standard input.
     *
     * @param input test data
     */
    public static void setIn(String input) {
        ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(bais);
    }

    /**
     * Mock standard output.
     *
     * @return output as {@link String}
     */
    public static ByteArrayOutputStream setOut() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        return baos;
    }
}
