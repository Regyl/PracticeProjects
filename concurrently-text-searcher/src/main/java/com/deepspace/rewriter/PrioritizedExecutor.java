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
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;

@Log(topic = "PrioritizedExecutor")
@RequiredArgsConstructor
public class PrioritizedExecutor extends Thread {

    private static final String NEW_FILE_POSTFIX = "_out.";
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private final File file;
    private final CountDownLatch latch;
    private final String pathToWrite;
    private final ConcurrentMap<String, Double> resultTime;

    @Override
    public void run() {
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

            long timeEnd = System.nanoTime();
            resultTime.put(file.getName(), CountUtils.millsBetween(timeStart, timeEnd));
        } catch (IOException e) {
            log.warning(e.getMessage());
        } finally {
            latch.countDown();
        }
    }
}
