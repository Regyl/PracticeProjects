package com.deepspace.rsaaes.algorithm.aes;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public abstract class AesConstants {

    protected static final Charset CHARSET = StandardCharsets.UTF_8;
    protected static final String ALGORITHM_NAME = "AES";

    protected AesConstants() {}
}
