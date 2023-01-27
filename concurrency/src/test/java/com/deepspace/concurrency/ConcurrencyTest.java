package com.deepspace.concurrency;

import org.testng.annotations.Test;

import java.util.Collections;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrencyTest {

    @Test
    void test() {
        AtomicInteger counter = new AtomicInteger();
        AtomicInteger counter2 = new AtomicInteger();

        ForkJoinTask.invokeAll(Collections.nCopies(100, ForkJoinTask.adapt(() -> {
            counter.incrementAndGet();
            counter2.incrementAndGet();
        })));
    }
}
