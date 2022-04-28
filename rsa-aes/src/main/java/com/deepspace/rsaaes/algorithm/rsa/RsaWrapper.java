package com.deepspace.rsaaes.algorithm.rsa;

import lombok.extern.java.Log;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import static java.util.logging.Level.WARNING;

@Log
public class RsaWrapper extends RsaConstants {

    private Cipher wrapper;

    public RsaWrapper(PublicKey publicKey) throws InvalidKeyException {
        try {
            wrapper = Cipher.getInstance(ALGORITHM_NAME);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
            log.log(WARNING, e.getMessage());
        }
        wrapper.init(Cipher.WRAP_MODE, publicKey);
    }

    /**
     *
     * @param secretKey
     * @return
     * @throws IllegalBlockSizeException
     * @throws InvalidKeyException
     */
    public byte[] wrap(Key secretKey) throws IllegalBlockSizeException, InvalidKeyException {
        return wrapper.wrap(secretKey);
    }
}
