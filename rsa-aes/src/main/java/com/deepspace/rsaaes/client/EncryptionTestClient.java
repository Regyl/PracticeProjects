package com.deepspace.rsaaes.client;

import com.deepspace.rsaaes.algorithm.aes.AesConstants;
import com.deepspace.rsaaes.algorithm.aes.AesDecryptor;
import com.deepspace.rsaaes.algorithm.rsa.RsaWrapper;
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
public class EncryptionTestClient extends AesConstants {

    private static final int DEFAULT_KEY_SIZE = 256;

    private static KeyGenerator generator;

    private final RsaWrapper rsaWrapper;

    private AesDecryptor aesDecryptor;

    static {
        try {
            generator = KeyGenerator.getInstance(ALGORITHM_NAME);
            generator.init(DEFAULT_KEY_SIZE);
        } catch (NoSuchAlgorithmException e) {
            log.log(WARNING, e.getMessage());
        }
    }

    public EncryptionTestClient(PublicKey publicKey) throws InvalidKeyException {
        this.rsaWrapper = new RsaWrapper(publicKey);
    }

    public byte[] generateAndSendSecretKey() {
        SecretKey secretKey = generator.generateKey();
        this.aesDecryptor = new AesDecryptor(secretKey);
        try {
            return rsaWrapper.wrap(secretKey);
        } catch (IllegalBlockSizeException | InvalidKeyException e) {
            log.log(WARNING, e.getMessage());
            return null;
        }
    }

    public String decryptMessage(byte[] payload) throws IllegalBlockSizeException, BadPaddingException {
        return aesDecryptor.decryptMessage(payload);
    }

}
