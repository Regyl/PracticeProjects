package com.deepspace.rewriter;

import lombok.extern.java.Log;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

@Log(topic = "PrioritizedDirectoryFileReverseRewriter")
public class PrioritizedFileReverseRewriter extends AbstractFileReverseRewriter {

    public PrioritizedFileReverseRewriter(String path) {
        super(path);
    }

    @Override
    public Map<String, Double> doDirectoryRewrite(Set<File> filesToProcess) {
        ConcurrentMap<String, Double> resultTime = new ConcurrentHashMap<>(filesToProcess.size() + 1, 1);
        CountDownLatch latch = new CountDownLatch(filesToProcess.size());

        List<PrioritizedExecutor> prioritizedExecutors = filesToProcess.parallelStream()
                .map(file -> new PrioritizedExecutor(file, latch, pathToWrite, resultTime))
                .toList();
        IntStream.range(0, filesToProcess.size()).forEach(i -> {
            PrioritizedExecutor executor = prioritizedExecutors.get(i);
            executor.setPriority(getThreadPriority(filesToProcess.size() - i));
//            executor.setPriority(Thread.MAX_PRIORITY);
            executor.setDaemon(Boolean.FALSE);
            executor.start();
        });

        waitForThreads(latch);

        return resultTime;
    }

    @Override
    public String getRewriterName() {
        return "PrioritizedDirectoryFileReverseRewriter";
    }

    private int getThreadPriority(int i) {
        if (i < Thread.NORM_PRIORITY || i > (Thread.MAX_PRIORITY - 1)) {
            return Thread.NORM_PRIORITY;
        }

        return i;
    }
}
