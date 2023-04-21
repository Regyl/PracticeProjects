package com.deepspace.rewriter;

import java.io.File;
import java.util.Map;
import java.util.Set;

public interface DirectoryFileReverseRewriter {

    Map<String, Double> doDirectoryRewrite(Set<File> filesToProcess);

    String getRewriterName();
}
