package com.deepspace.rsaaes.algorithm.aes;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AesEncryptor extends AesConstants {

    private Cipher cipher;
    private SecretKey key;

    public AES(SecretKey secretKey, IvParameterSpec iv) {
        this.key = secretKey;
        try {
            cipher = Cipher.getInstance(ALGORITHM_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | InvalidKeyException e) {

        }
    }
}
