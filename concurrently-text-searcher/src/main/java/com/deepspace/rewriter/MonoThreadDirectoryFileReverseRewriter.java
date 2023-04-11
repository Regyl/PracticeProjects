package com.deepspace.rewriter;

import lombok.extern.java.Log;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Log(topic = "MonoThreadDirectoryFileReverseRewriter")
public class MonoThreadDirectoryFileReverseRewriter extends AbstractDirectoryFileReverseRewriter {

    public MonoThreadDirectoryFileReverseRewriter(String path) {
        super(path);
    }

    @Override
    public String getRewriterName() {
        return "MonoThreadDirectoryFileReverseRewriter";
    }

    @Override
    public void doDirectoryRewrite() {
        try (Stream<Path> filePaths = Files.walk(Paths.get(path + "/old"), 1, FileVisitOption.FOLLOW_LINKS)) {
            filePaths
                    .map(Path::toFile)
                    .filter(item -> !item.isDirectory())
                    .forEach(this::doFileRewrite);
        } catch (IOException e) {
            log.warning(e.getMessage());
        }
    }
}
