import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotSame;

public class HackPasswordTest {
    private static final String INITIAL_HASH = "40682260CC011947FC2D0B1A927138C5";
    @Test
    private void positiveMainCaseTest() {
        int threadsNumber = 9;

        String password = PasswordHacker.calculatePassword(threadsNumber, INITIAL_HASH);
        String hash = GenerateHash.hashPassword(password);

        assertEquals(hash, INITIAL_HASH);
    }

    @Test
    private void negativeMainCaseTest() {
        int threadsNumber = 9;

        String someStrForTest = "smsmbls";
        String someStringHash = GenerateHash.hashPassword(someStrForTest);
        String password = PasswordHacker.calculatePassword(threadsNumber, someStringHash);
        String recalculatedHash = GenerateHash.hashPassword(password);

        assertNotSame(recalculatedHash, INITIAL_HASH);
    }
}