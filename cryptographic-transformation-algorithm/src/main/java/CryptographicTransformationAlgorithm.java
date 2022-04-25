import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptographicTransformationAlgorithm {

    private static final String MESSAGE = "DKJSFBHSDBFLJZDNCNBDHKHBSDJHCBLIDYBCUBSDAKLHBASNDBhjlhjghukg";

    public static void main(String[] args) {
        String MAC = Encryptor.MAC(MESSAGE);
        System.out.println("Изначальное сообщение: " + MESSAGE);
        System.out.println("Message Authentication Code: " + MAC);
    }

    private static class Encryptor {

        private static MessageDigest MD = null;

        static {
            try {
                MD = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        private static String MAC(String message) {
            byte[] symbol = {0};
            String s = new String(symbol, StandardCharsets.UTF_8);
            for(int i=0; i<message.length(); i+=64) {
                if(message.length() - i*2 < 0) {
                    s = s.repeat(i*2 - message.length());
                    message = message.concat(s);
                } else if (message.length() < 64) {
                    s = s.repeat(64 - message.length());
                    message = message.concat(s);
                }
                byte[] array = message.substring(i, i + 64).getBytes(StandardCharsets.UTF_8);
                MD.update(array);
            }
            return new String(MD.digest(), StandardCharsets.UTF_8);
        }

    }
}
