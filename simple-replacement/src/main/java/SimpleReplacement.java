
public class SimpleReplacement {

    private static final String ORIGINAL    = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String REPLACEMENT = "ёйцукенгшщзхъфывапролджэячсмитьбюЁЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ";

    public static void main(String[] args) {
        String message = "Неоспоримая подпись может быть верифицирована только путем непосредственного взаимодействия с подписывающей стороной А.";
        System.out.println("Before encoding: " + message);
        message = encodeWithSimpleReplacement(message);
        System.out.println("After encoding: " + message);
    }

    private static String encodeWithSimpleReplacement(String message) {
        for(int i=0; i<ORIGINAL.length(); i++ ) {
            String replacementChar = REPLACEMENT.substring(i, i+1);
            System.out.println("DEBUG: " + replacementChar);
            message = message.replaceAll("["+ORIGINAL.charAt(i)+"]", replacementChar);
        }
        return message;
    }

}
