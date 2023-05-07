package com.deepspace;

public class MainNovikov {

    public static void main(String[] args) {
        RewriteUtils rewriteUtils = new RewriteUtils();
        rewriteUtils.checkOnExistenceAndStart(args, 5, ResultProcessType.NONE, FileReverseRewriterType.MULTI_THREAD, Boolean.TRUE);
    }
}