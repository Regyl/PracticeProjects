import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Example of runnable without usage of {@link ThreadLocal}.
 */
public class WithoutThreadLocal extends AbstractThreadLocalExample {

    private Integer value;

    public WithoutThreadLocal(Integer value) {
        this.value = value;
    }

    @Override
    protected Consumer<Integer> setter() {
        return integer -> value = integer;
    }

    @Override
    protected Supplier<Integer> getter() {
        return () -> value;
    }
}
