import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Example of runnable with use of {@link ThreadLocal}.
 */
public class WithThreadLocal extends AbstractThreadLocalExample {

    //Yes, you can mark it as final
    private ThreadLocal<Integer> value;

    public WithThreadLocal(ThreadLocal<Integer> value) {
        this.value = value;
    }

    @Override
    protected Consumer<Integer> setter() {
        return value::set;
    }

    @Override
    protected Supplier<Integer> getter() {
        return value::get;
    }
}
