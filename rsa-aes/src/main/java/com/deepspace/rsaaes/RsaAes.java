package com.deepspace.rsaaes;

import com.deepspace.rsaaes.algorithm.aes.AesConstants;
import com.deepspace.rsaaes.algorithm.aes.AesEncryptor;
import com.deepspace.rsaaes.algorithm.rsa.RSA;
import com.deepspace.rsaaes.algorithm.rsa.RsaDecryptor;
import com.deepspace.rsaaes.client.EncryptionTestClient;
import lombok.extern.java.Log;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.KeyPair;

import static java.util.logging.Level.*;

@Log
public class RsaAes extends AesConstants {

    private static final String MESSAGE = "Hello, world!";

    private static AesEncryptor aesEncryptor;
    private static EncryptionTestClient client;

    public static void main(String[] args) throws IllegalBlockSizeException, BadPaddingException {
        initialize();

        byte[] encryptMessage = aesEncryptor.encrypt(MESSAGE);
        String message = client.decryptMessage(encryptMessage);
        log.log(FINEST, message);
    }

    private static void initialize() {
        RSA rsa = RSA.getInstance();
        KeyPair rsaKeyPair = rsa.generateKeys();

        try {
            //initialize RSA decryptor
            RsaDecryptor rsaDecryptor = new RsaDecryptor(rsaKeyPair.getPrivate());

            //initialize client
            client = new EncryptionTestClient(rsaKeyPair.getPublic());
            byte[] encodedSymmetricKey = client.generateAndSendSecretKey();

            //initialize AES encryptor
            SecretKey symmetricKey = new SecretKeySpec(rsaDecryptor.decrypt(encodedSymmetricKey), ALGORITHM_NAME);
            aesEncryptor = new AesEncryptor(symmetricKey);
        } catch (InvalidKeyException | NullPointerException | IllegalBlockSizeException | BadPaddingException e) {
            log.log(WARNING, e.getMessage());
        }
    }

}
