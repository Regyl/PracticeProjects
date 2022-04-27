package com.deepspace.rsaaes.algorithm.rsa;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public abstract class RsaConstants {

    protected static final Charset CHARSET = StandardCharsets.UTF_8;
    protected static final String ALGORITHM_NAME = "RSA";

    protected RsaConstants() {}
}
