import org.testng.annotations.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {

    @Test
    public void withoutThreadLocal() throws InterruptedException {
        int threadSize = 2;
        ExecutorService executor = Executors.newFixedThreadPool(threadSize);

        WithoutThreadLocal threadLocal = new WithoutThreadLocal(100);
        for (int i = 0; i < threadSize; i++) {
            executor.submit(threadLocal);
        }

        executor.awaitTermination(3, TimeUnit.SECONDS);
    }

    @Test
    public void withThreadLocal() throws InterruptedException {
        int threadSize = 2;
        ExecutorService executor = Executors.newFixedThreadPool(threadSize);

        WithThreadLocal threadLocal = new WithThreadLocal(ThreadLocal.withInitial(() -> 100));
        for (int i = 0; i < threadSize; i++) {
            executor.submit(threadLocal);
        }

        executor.awaitTermination(3, TimeUnit.SECONDS);
    }
}
