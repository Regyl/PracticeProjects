public class ConcurrencyApplication {

    private static final String INITIAL_HASH = "40682260CC011947FC2D0B1A927138C5";

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String password = PasswordHacker.calculatePassword(4, INITIAL_HASH);
        System.out.println("RESULT " + password);
        System.out.println("GENERAL TIME " + String.valueOf(System.currentTimeMillis()-start));
        System.exit(0);
    }
}
