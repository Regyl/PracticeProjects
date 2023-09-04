import org.testng.annotations.Test;
import utils.InOutMockUtils;
import yandex.PoisonBuckets;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

public class PoisonBucketsTest {

    @Test
    public void idk1() {
        String input = """
                5
                1 1 5 5 5
                """;
        InOutMockUtils.setIn(input);
        ByteArrayOutputStream baos = InOutMockUtils.setOut();

        PoisonBuckets.main(null);

        Integer result = Integer.parseInt(baos.toString(StandardCharsets.UTF_8));
        assertThat(result).isEqualTo(4);
    }

    @Test
    public void idk2() {
        String input = """
                3
                3 2 1
                """;
        InOutMockUtils.setIn(input);
        ByteArrayOutputStream baos = InOutMockUtils.setOut();

        PoisonBuckets.main(null);

        Integer result = Integer.parseInt(baos.toString(StandardCharsets.UTF_8));
        assertThat(result).isEqualTo(-1);
    }
}
