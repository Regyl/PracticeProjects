package com.deepspace.rewriter;

import lombok.extern.java.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

@Log(topic = "MultiThreadDirectoryFileReverseRewriter")
public class MultiThreadDirectoryFileReverseRewriter extends AbstractDirectoryFileReverseRewriter {

    public MultiThreadDirectoryFileReverseRewriter(String path) {
        super(path);
    }

    @Override
    public String getRewriterName() {
        return "MultiThreadDirectoryFileReverseRewriter";
    }

    @Override
    public void doDirectoryRewrite() {
        try (Stream<Path> filePaths = Files.walk(Paths.get(path + "/old"), 1, FileVisitOption.FOLLOW_LINKS)) {
            List<File> files = filePaths
                    .map(Path::toFile)
                    .filter(item -> !item.isDirectory())
                    .toList();

            CountDownLatch latch = new CountDownLatch(files.size());
            ExecutorService executor = Executors.newWorkStealingPool(files.size());
            files.forEach(item -> executor.submit(() -> {
                try {
                    this.doFileRewrite(item);
                } finally {
                    latch.countDown();
                }
            }));

            try {
                latch.await();
            } catch (InterruptedException e) {
                log.warning(e.getMessage());
            }
        } catch (IOException e) {
            log.warning(e.getMessage());
        }
    }
}
