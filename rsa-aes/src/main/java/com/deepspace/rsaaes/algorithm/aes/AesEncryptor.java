package com.deepspace.rsaaes.algorithm.aes;

import lombok.extern.java.Log;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import static java.util.logging.Level.*;

@Log
public class AesEncryptor extends AesConstants {

    private Cipher cipher;

    public AesEncryptor(SecretKey secretKey) {
        try {
            cipher = Cipher.getInstance(ALGORITHM_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            log.log(WARNING, e.getMessage());
        }
    }

    public byte[] encrypt(String message) throws IllegalBlockSizeException, BadPaddingException {
        return cipher.doFinal(message.getBytes(CHARSET));
    }
}
