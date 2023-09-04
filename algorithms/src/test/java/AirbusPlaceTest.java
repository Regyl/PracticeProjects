import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import utils.InOutMockUtils;
import yandex.AirbusPlace;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public class AirbusPlaceTest {

    @Test
    public void idk() {
        String input = """
                4
                ..._.#.
                .##_...
                .#._.##
                ..._...
                7
                2 left aisle
                3 right window
                2 left window
                3 left aisle
                1 right window
                2 right window
                1 right window
                """;
        InOutMockUtils.setIn(input);
        ByteArrayOutputStream baos = InOutMockUtils.setOut();

        AirbusPlace.main(null);

        String expectedOutput = """
                Passengers can take seats: 1B 1C
                .XX_.#.
                .##_...
                .#._.##
                ..._...
                Passengers can take seats: 2D 2E 2F
                .##_.#.
                .##_XXX
                .#._.##
                ..._...
                Passengers can take seats: 4A 4B
                .##_.#.
                .##_###
                .#._.##
                XX._...
                Cannot fulfill passengers requirements
                Passengers can take seats: 1F
                .##_.#X
                .##_###
                .#._.##
                ##._...
                Passengers can take seats: 4E 4F
                .##_.##
                .##_###
                .#._.##
                ##._.XX
                Cannot fulfill passengers requirements
                """;
        String actualOutput = baos.toString(StandardCharsets.UTF_8);
        Assertions.assertThat(actualOutput).isEqualToIgnoringNewLines(expectedOutput);
    }
}
