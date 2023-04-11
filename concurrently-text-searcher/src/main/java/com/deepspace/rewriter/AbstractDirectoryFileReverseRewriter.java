package com.deepspace.rewriter;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.io.input.ReversedLinesFileReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Log(topic = "AbstractDirectoryFileReverseRewriter")
public abstract class AbstractDirectoryFileReverseRewriter implements DirectoryFileReverseRewriter {

    protected static final String NEW_FILE_PREFIX = "new_";
    protected static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    protected final String path;

    protected void doFileRewrite(File file) {
        String newFileName = NEW_FILE_PREFIX + file.getName();
        File newFile = new File(path + "/new/" + newFileName);

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
    }
}
