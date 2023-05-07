package com.deepspace.rewriter;

import lombok.extern.java.Log;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log(topic = "MultiThreadDirectoryFileReverseRewriter")
public class MultiThreadFileReverseRewriter extends AbstractFileReverseRewriter {

    public MultiThreadFileReverseRewriter(String path) {
        super(path);
    }

    @Override
    public String getRewriterName() {
        return "MultiThreadDirectoryFileReverseRewriter";
    }

    @Override
    public Map<String, Double> doDirectoryRewrite(Set<File> filesToProcess) {
        Map<String, Double> resultTime = new HashMap<>(filesToProcess.size() + 1, 1);
        CountDownLatch latch = new CountDownLatch(filesToProcess.size());
        ExecutorService executor = Executors.newWorkStealingPool(filesToProcess.size());
        filesToProcess.forEach(item -> executor.submit(() -> {
            try {
                double time = this.doFileRewrite(item);
                resultTime.put(item.getName(), time);
            } finally {
                latch.countDown();
            }
        }));

        waitForThreads(latch);
        executor.shutdownNow();

        return resultTime;
    }
}
