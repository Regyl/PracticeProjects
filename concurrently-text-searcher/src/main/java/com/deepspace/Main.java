package com.deepspace;

import com.deepspace.rewriter.DirectoryFileReverseRewriter;
import com.deepspace.rewriter.MonoThreadDirectoryFileReverseRewriter;
import com.deepspace.rewriter.MultiThreadDirectoryFileReverseRewriter;
import com.deepspace.rewriter.PrioritizedDirectoryFileReverseRewriter;

public class Main {

    public static void main(String[] args) {
        String path = "concurrently-text-searcher/src/main/resources/files";

         callRewriter(new MonoThreadDirectoryFileReverseRewriter(path));
         callRewriter(new MultiThreadDirectoryFileReverseRewriter(path));
         callRewriter(new PrioritizedDirectoryFileReverseRewriter(path));
    }

    private static void callRewriter(DirectoryFileReverseRewriter rewriter) {
        long startTime = System.nanoTime();
        rewriter.doDirectoryRewrite();
        long endTime = System.nanoTime();

        System.out.println(rewriter.getRewriterName() + " duration in seconds: " + (endTime - startTime) / 1_000_000_000.0);
    }
}