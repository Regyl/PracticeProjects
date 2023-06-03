package com.deepspace;

public class MainRewrite {

    public static void main(String[] args) {
        RewriteUtils rewriteUtils = new RewriteUtils();
        rewriteUtils.checkOnExistenceAndStart(
                args,
                args.length - 1,
                ResultProcessType.NONE,
                FileReverseRewriterType.PRIORITIZED_THREAD,
                Boolean.TRUE
        );
    }
}