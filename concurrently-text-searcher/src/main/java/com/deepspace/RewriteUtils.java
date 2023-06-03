package com.deepspace;


import com.deepspace.rewriter.FileReverseRewriter;
import lombok.extern.java.Log;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.time.Clock;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Log(topic = "DirectoryUtils")
public class RewriteUtils {

    private static final int LINE_SIZE = 5;
    private static final int TABLE_SIZE = 10;
    private static final String NEW_FOLDER_NAME = "/new/";
    private static final String OLD_FOLDER_NAME = "/old/";

    public void checkOnExistenceAndStart(String[] args, int fileSize, ResultProcessType processType, FileReverseRewriterType rewriterType, boolean enableLogging) {
        System.out.printf("Main thread start at %s %n", LocalTime.now(Clock.systemUTC()));
        if (args == null || args.length <= 2) {
            throw new IllegalArgumentException("Недопустимое число аргументов");
        }

        String path = args[0];
        if (!Files.isDirectory(Path.of(path), LinkOption.NOFOLLOW_LINKS)) {
            throw new IllegalArgumentException(args[0] + " не является директорией");
        }

        FileReverseRewriter rewriter = FileReverseRewriterType.instantiate(rewriterType, path + NEW_FOLDER_NAME);
        if (processType == ResultProcessType.NONE) {
            callOnce(args, path, fileSize, rewriter, enableLogging);
        } else {
            int concurrentFileLimit = 1;
            List<List<Pair<Long, Long>>> table = new ArrayList<>();
            for (int j = 0; j < TABLE_SIZE; j++) {
                List<Pair<Long, Long>> line = new ArrayList<>();
                for (int index = 0; index < LINE_SIZE; index++) {
                    var cell = callOnce(args, path, concurrentFileLimit, rewriter, enableLogging);
                    line.add(cell);
                }
                table.add(line);
                concurrentFileLimit += 1;
            }

            processResult(processType, table);
        }

        System.out.printf("Main thread end at %s %n", LocalTime.now(Clock.systemUTC()));
    }

    private Pair<Long, Long> callOnce(String[] args, String path, int concurrentFileLimit, FileReverseRewriter rewriter, boolean enableLogging) {
        Set<File> filesToProcess = new HashSet<>(args.length);
        int start = 1;
        for (int i = start; i < args.length && i < (concurrentFileLimit + start); i++) {
            File fileToProcess = new File(path + OLD_FOLDER_NAME + args[i]);

            if (fileToProcess.exists() && !fileToProcess.isDirectory()) {
                filesToProcess.add(fileToProcess);
            } else {
                log.warning("Файл не существует или не является файлом, пропускаю: " + args[i]);
            }
        }

        return logRewriterCall(rewriter, filesToProcess, enableLogging);
    }

    private Pair<Long, Long> logRewriterCall(FileReverseRewriter rewriter, Set<File> filesToProcess, boolean enableLogging) {
        long startTime = System.nanoTime();
        Map<String, Double> resultTime = rewriter.doDirectoryRewrite(filesToProcess);
        long endTime = System.nanoTime();

        double generalProcessTime = CountUtils.millsBetween(startTime, endTime);
        if (enableLogging) {
            System.out.printf("General time for %s is %s mills %n", rewriter.getRewriterName(), generalProcessTime);
        }

        long avgProcessTime = 0;
        for (var item : resultTime.entrySet()) {
            avgProcessTime += item.getValue();
            if (enableLogging) {
                System.out.printf("File %s took %s mills %n", item.getKey(), item.getValue());
            }
        }

        if (enableLogging) {
            System.out.printf("Average process time: %s mills %n", avgProcessTime /filesToProcess.size());
        }

        return Pair.of(Double.valueOf(generalProcessTime).longValue(), avgProcessTime / filesToProcess.size());
    }

    private void processResult(ResultProcessType processType, List<List<Pair<Long, Long>>> table) {

        File resCsv = new File("C:\\Users\\novik\\Custom\\repos\\PracticeProjects\\concurrently-text-searcher\\src\\main\\resources\\" + "result.csv");
        StringBuilder sb = new StringBuilder();

        switch (processType) {
            case GENERAL_TIME -> IntStream.range(1, table.size() + 1).forEach(i -> {
                sb.append(i).append(",");

                var line = table.get(i - 1);
                line.forEach(cell -> sb.append(cell.getLeft()).append(","));

                long avgTime = CountUtils.calculateAvgTime(line);
                sb.append(avgTime).append("\n");
            });
            case AVG_TIME -> table.forEach(line -> {
                long avgFileProcessTime = line.stream()
                        .map(Pair::getRight)
                        .reduce(Long::sum)
                        .orElseThrow(() -> new IllegalArgumentException("Пустая строка результата")) / line.size();
                sb.append(avgFileProcessTime).append(",");
            });
            case NONE -> {}
        }

        try (FileOutputStream fos = new FileOutputStream(resCsv)) {
            fos.write(sb.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void logThreadWithGroups() {
        /*ThreadGroup[] groups = new ThreadGroup[100];
        Thread.currentThread().getThreadGroup().getParent().enumerate(groups);

        Stream.of(groups).filter(Objects::nonNull).forEach(group -> {
            System.out.println("\nGroup name: " + group.getName() + "\n Threads: ");
            Thread[] threads = new Thread[100];
            group.enumerate(threads);

            Stream.of(threads).filter(Objects::nonNull).forEach(thread -> {
                System.out.println(thread.getName() + " isDaemon: " + thread.isDaemon());
            });
        });*/

        Set<Thread> threads = Thread.getAllStackTraces().keySet();
        System.out.printf("%-15s \t %-15s \t %-15s \t %s\n", "Name", "State", "Priority", "isDaemon");
        for (Thread t : threads) {
            System.out.printf("%-15s \t %-15s \t %-15d \t %s\n", t.getName(), t.getState(), t.getPriority(), t.isDaemon());
        }
    }
}
