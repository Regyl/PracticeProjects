import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.LockSupport;

public class PasswordHacker {

    private static final int ALPHABET_POWER = 26;
    private static final int WORD_LENGTH = 7;
    private static final String DEFAULT_RESPONSE = "NOT_FOUND";

    public static String calculatePassword(int threadsNumber, String initialHash) {
        List<Future<String>> responses = new ArrayList<>(threadsNumber);
        long step = calculateStep(threadsNumber);

        for (int i=0; i < threadsNumber; i++) {
            PasswordChecker item = new PasswordChecker(step*i, step*(i+1), initialHash,
                    WORD_LENGTH, DEFAULT_RESPONSE, ALPHABET_POWER);
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Future<String> future = executorService.submit(item);
            responses.add(future);
        }

        try {
            while (true) {
                final long periodInNanos = 1000000000L * 10;
                long finishedThreads = 0;
                for (Future<String> item : responses) {
                    if (item.isDone()) {
                        finishedThreads++;

                        if (!DEFAULT_RESPONSE.equals(item.get())) {
                            return item.get();
                        }
                    }

                    if (finishedThreads == threadsNumber) {
                        return DEFAULT_RESPONSE;
                    }
                }
                LockSupport.parkNanos(periodInNanos);
            }
        } catch (InterruptedException | ExecutionException e) {
            System.err.print(e.getMessage());
            return DEFAULT_RESPONSE;
        }
    }

    private static long calculateStep(int threadsNumber) {
        return Math.round(Math.pow(ALPHABET_POWER, WORD_LENGTH)/threadsNumber+1);
    }
}
