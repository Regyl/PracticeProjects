import java.nio.charset.StandardCharsets;

public class SecondCustomEncryption {

    private static final String MESSAGE = "К понятию «конфиденциальная информация» тесно примыкают понятия «государственная тайна» и «коммерческая тайна».";
    //A - 1040, Я - 1071, а - 1072, я - 1103
    public static void main(String[] args) {
        System.out.println("Текст до шифрования: " + MESSAGE);
        String resultMessage = Encryptor.encryptionVigenere(MESSAGE);
        System.out.println("Текст после шифра Виженера:  " + resultMessage);
        resultMessage = Encryptor.XOR(resultMessage);
        System.out.println("Текст после гаммирования: " + resultMessage);
    }

    private static class Encryptor {

        private static final int ALPHABET_STRANGE = 33;

        private static final byte[] KEY;

        private static final int[] SHIFTS = {4, 9, 2};

        static {
            KEY = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        }

        //Шифр Виженера
        protected static String encryptionVigenere(String message) {
            for(int shift : SHIFTS) {
                message = encryptionCaesar(message, shift);
            }
            return message;
        }

        //Шифр Цезаря
        protected static String encryptionCaesar(String message, int shift) {
            StringBuilder sb = new StringBuilder();
            for(char item : message.toCharArray()) {
                if(item > 'А' && item < 'я') {
                    int result = (int) item + shift;
                    if(result > 'я') {
                        result -= ALPHABET_STRANGE*2;
                    }
                    sb.append((char) result);
                } else {
                    sb.append(item);
                }
            }
            return sb.toString();
        }

        protected static String XOR(String message) {
            byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<bytes.length; i++) {
                int item = bytes[i] ^ KEY[i%KEY.length];
                sb.append((char) item);
            }
            return sb.toString();
        }
    }
}