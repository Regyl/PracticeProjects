package com.deepspace.rewriter;

import com.deepspace.CountUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.io.input.ReversedLinesFileReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.LocalTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

@RequiredArgsConstructor
@Log(topic = "AbstractDirectoryFileReverseRewriter")
public abstract class AbstractFileReverseRewriter implements FileReverseRewriter {

    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private static final String NEW_FILE_POSTFIX = "_out.";

    protected final String pathToWrite;

    protected double doFileRewrite(File file) {
        System.out.printf("File %s starts at: %s %n", file.getName(), LocalTime.now(Clock.systemUTC()));
        long timeStart = System.nanoTime();
        String[] splitName = file.getName().split("\\.");
        if (splitName.length != 2) {
            throw new IllegalArgumentException("Недопустимое название файла: " + file.getName());
        }
        String newFileName = splitName[0] + NEW_FILE_POSTFIX + splitName[1];
        File newFile = new File(pathToWrite + newFileName);

        try (
                FileOutputStream fos = new FileOutputStream(newFile);
                ReversedLinesFileReader fileReader = new ReversedLinesFileReader(file, DEFAULT_CHARSET);
        ) {

            String payload = "";
            do {
                payload += System.lineSeparator();
                fos.write(payload.getBytes(DEFAULT_CHARSET));

                payload = fileReader.readLine();
            } while (payload != null);
        } catch (IOException e) {
            log.warning(e.getMessage());
        }

        long timeEnd = System.nanoTime();
        System.out.printf("File %s ends at: %s %n", file.getName(), LocalTime.now(Clock.systemUTC()));
        return CountUtils.millsBetween(timeStart, timeEnd);
    }

    protected void waitForThreads(CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            log.warning(e.getMessage());
        }
    }
}
