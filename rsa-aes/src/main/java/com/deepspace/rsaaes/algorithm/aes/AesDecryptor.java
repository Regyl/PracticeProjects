package com.deepspace.rsaaes.algorithm.aes;

import lombok.extern.java.Log;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static java.util.logging.Level.WARNING;

@Log
public class AesDecryptor extends AesConstants {

    private Cipher cipher;

    public AesDecryptor(SecretKey secretKey) {
        try {
            cipher = Cipher.getInstance(ALGORITHM_NAME);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            log.log(WARNING, e.getMessage());
        }
    }

    public String decryptMessage(byte[] payload) throws IllegalBlockSizeException, BadPaddingException {
        return new String(cipher.doFinal(payload), CHARSET);
    }
}
