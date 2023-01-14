import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;

public class PasswordChecker implements Callable<String> {

    private static final int A_AS_INTEGER ='a';

    /**
     * Номер слова с которого начинается перебор
     */
    private final long start;

    /**
     * Верхняя граница перебора.
     */
    private final long end;

    /**
     * Число символов в пароле.
     */
    private final int symbolQuantity;

    /**
     * Хэш зашифрованного пароля.
     */
    private final String initialHash;

    /**
     * Ответ на случай, если не найден исходный пароль.
     */
    private final String defaultResponse;

    /**
     * Сила алфавита.
     */
    private final int alphabetPower;

    /**
     * Конструктор.
     *
     * @param start начальное значение для перебора
     * @param end конечное значение для перебора
     * @param symbolQuantity - число символов в зашифрованном пароле
     * @param initialHash хэш зашифрованного пароля
     * @param defaultResponse ответ по умолчанию
     * @param alphabetPower сила словаря
     */
    public PasswordChecker(long start, long end, String initialHash, int symbolQuantity, String defaultResponse, int alphabetPower) {
        this.start = start;
        this.end = end;
        this.symbolQuantity = symbolQuantity;
        this.initialHash = initialHash;
        this.defaultResponse = defaultResponse;
        this.alphabetPower = alphabetPower;
    }

    //TODO PhantomReference for hash
    @Override
    public String call() {
        for (long i=start; i < end; i++) {
            char[] next = calculateWord(i);
            String password = new String(next);
            WeakReference<String> hash = new WeakReference<>(GenerateHash.hashPassword(password));
            if (initialHash.equals(hash.get())) {
                return password;
            }
        }
        return defaultResponse;
    }

    /**
     * Получение пароля по номеру.
     *
     * @param wordAsInt значение для получения строки
     * @return пароль
     */
    private char[] calculateWord(long wordAsInt) {
        char[] password = new char[symbolQuantity];
        for (int i=symbolQuantity-1; i>=0; i--) {
            password[i] = (char) (wordAsInt % alphabetPower + A_AS_INTEGER);
            wordAsInt = wordAsInt / alphabetPower;
        }
        return password;
    }
}
