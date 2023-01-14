import java.util.Random;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Class with main logic.
 */
public abstract class AbstractThreadLocalExample implements Runnable {

    private static final Random RND = new Random();

    @Override
    public void run() {
        LockSupport.parkNanos(RND.nextInt(1_000_000_000, 2_000_000_000));

        System.out.println(getThreadName() + ", before value changing: " + getter().get());
        setter().accept(RND.nextInt(1, 9));
    }

    /**
     * Setter for our value.
     *
     * @return consumer
     */
    protected abstract Consumer<Integer> setter();

    /**
     * Getter for our value.
     *
     * @return supplier
     */
    protected abstract Supplier<Integer> getter();

    private String getThreadName() {
        return Thread.currentThread().getName();
    }
}
