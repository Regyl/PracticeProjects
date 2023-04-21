package com.deepspace;


import com.deepspace.rewriter.DirectoryFileReverseRewriter;
import com.deepspace.rewriter.MonoThreadDirectoryFileReverseRewriter;
import com.deepspace.rewriter.MultiThreadDirectoryFileReverseRewriter;
import com.deepspace.rewriter.PrioritizedDirectoryFileReverseRewriter;
import lombok.extern.java.Log;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

@Log(topic = "DirectoryUtils")
public class DirectoryUtils {

    private static final String NEW_FOLDER_NAME = "/new/";
    private static final String OLD_FOLDER_NAME = "/old/";
    private static final int CONCURRENT_FILE_LIMIT = 10;

    public void checkOnExistenceAndStart(String[] args) {
        if (args == null || args.length <= 2) {
            throw new IllegalArgumentException("Недопустимое число аргументов");
        }

        String path = args[0];
        if (!Files.isDirectory(Path.of(path), LinkOption.NOFOLLOW_LINKS)) {
            throw new IllegalArgumentException(args[0] + " не является директорией");
        }

        Set<File> filesToProcess = new HashSet<>(args.length);
        int start = 1;
        for (int i = start; i < args.length && i < (CONCURRENT_FILE_LIMIT + start); i++) {
            File fileToProcess = new File(path + OLD_FOLDER_NAME + args[i]);

            if (fileToProcess.exists() && !fileToProcess.isDirectory()) {
                filesToProcess.add(fileToProcess);
            } else {
                log.warning("Файл не существует или не является файлом, пропускаю: " + args[i]);
            }
        }

        Stream.of(new MonoThreadDirectoryFileReverseRewriter(path + NEW_FOLDER_NAME),
                new MultiThreadDirectoryFileReverseRewriter(path + NEW_FOLDER_NAME),
                new PrioritizedDirectoryFileReverseRewriter(path + NEW_FOLDER_NAME))
                .forEach(item -> callRewriter(item, filesToProcess));
    }

    private void callRewriter(DirectoryFileReverseRewriter rewriter, Set<File> filesToProcess) {
        long startTime = System.nanoTime();
        Map<String, Double> resultTime = rewriter.doDirectoryRewrite(filesToProcess);
        long endTime = System.nanoTime();

        System.out.println(rewriter.getRewriterName() + " duration in mills: " + CountUtils.millsBetween(startTime, endTime));

        long avgProcessTime = 0;
        for (var item : resultTime.entrySet()) {
            avgProcessTime += item.getValue();
            String logItem = String.format("File %s took %s mills", item.getKey(), item.getValue());
            System.out.println(logItem);
        }

        System.out.println("Average process time: " + avgProcessTime * 1.0 /filesToProcess.size());
    }
}
