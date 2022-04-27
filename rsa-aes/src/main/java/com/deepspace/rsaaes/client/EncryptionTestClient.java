package com.deepspace.rsaaes.client;

import com.deepspace.rsaaes.algorithm.aes.AesDecryptor;
import com.deepspace.rsaaes.algorithm.rsa.RsaEncryptor;
import lombok.extern.java.Log;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import static java.util.logging.Level.*;

@Log
public class EncryptionTestClient {

    private static final String ALGORITHM_NAME = "AES";
    private static final int DEFAULT_KEY_SIZE = 256;

    private static KeyGenerator generator;

    private AesDecryptor aesDecryptor;
    private SecretKey secretKey;
    private RsaEncryptor rsaEncryptor;

    static {
        try {
            generator = KeyGenerator.getInstance(ALGORITHM_NAME);
            generator.init(DEFAULT_KEY_SIZE);
        } catch (NoSuchAlgorithmException e) {
            log.log(WARNING, e.getMessage());
        }
    }

    public EncryptionTestClient(PublicKey publicKey) throws InvalidKeyException {
        this.rsaEncryptor = new RsaEncryptor(publicKey);
    }

    public byte[] generateAndSendSecretKey() {
        secretKey = generator.generateKey();
        try {
            return rsaEncryptor.encrypt(secretKey.toString());
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            log.log(WARNING, e.getMessage());
            return null;
        }
    }

    public String decryptMessage(byte[] payload) throws IllegalBlockSizeException, BadPaddingException {
        return aesDecryptor.decryptMessage(payload);
    }

}
