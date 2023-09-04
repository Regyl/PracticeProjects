package com.deepspace.rewriter;

import java.io.File;
import java.util.Map;
import java.util.Set;

public interface FileReverseRewriter {

    Map<String, Double> doDirectoryRewrite(Set<File> filesToProcess);

    String getRewriterName();
}
