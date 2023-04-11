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
import java.util.stream.Stream;

@Log(topic = "PrioritizedDirectoryFileReverseRewriter")
public class PrioritizedDirectoryFileReverseRewriter extends AbstractDirectoryFileReverseRewriter {

    public PrioritizedDirectoryFileReverseRewriter(String path) {
        super(path);
    }

    @Override
    public void doDirectoryRewrite() {
        try (Stream<Path> filePaths = Files.walk(Paths.get(path + "/old"), 1, FileVisitOption.FOLLOW_LINKS)) {
            List<File> files = filePaths
                    .map(Path::toFile)
                    .filter(item -> !item.isDirectory())
                    .toList();

            CountDownLatch latch = new CountDownLatch(files.size());

            files.parallelStream()
                    .map(file -> new PrioritizedExecutor(file, latch, path))
                    .peek(thread -> thread.setPriority(Thread.MAX_PRIORITY))
                    .forEach(Thread::run);

            try {
                latch.await();
            } catch (InterruptedException e) {
                log.warning(e.getMessage());
            }
        } catch (IOException e) {
            log.warning(e.getMessage());
        }
    }

    @Override
    public String getRewriterName() {
        return "PrioritizedDirectoryFileReverseRewriter";
    }
}
