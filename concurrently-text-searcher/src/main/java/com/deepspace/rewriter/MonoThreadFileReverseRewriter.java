package com.deepspace.rewriter;

import lombok.extern.java.Log;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Log(topic = "MonoThreadDirectoryFileReverseRewriter")
public class MonoThreadFileReverseRewriter extends AbstractFileReverseRewriter {

    public MonoThreadFileReverseRewriter(String path) {
        super(path);
    }

    @Override
    public String getRewriterName() {
        return "MonoThreadDirectoryFileReverseRewriter";
    }

    @Override
    public Map<String, Double> doDirectoryRewrite(Set<File> filesToProcess) {
        Map<String, Double> resultTime = new HashMap<>(filesToProcess.size() + 1, 1);
        filesToProcess.forEach(item -> {
            double time = doFileRewrite(item);
            resultTime.put(item.getName(), time);
        });

        return resultTime;
    }
}
