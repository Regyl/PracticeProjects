package com.deepspace.rsaaes;

import com.deepspace.rsaaes.algorithm.aes.AesEncryptor;
import com.deepspace.rsaaes.algorithm.rsa.RSA;
import com.deepspace.rsaaes.algorithm.rsa.RsaUnwrapper;
import com.deepspace.rsaaes.client.EncryptionTestClient;
import lombok.extern.java.Log;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

import static java.util.logging.Level.*;

@Log
public class RsaAes {

    private static final String MESSAGE = "Hello, world!";

    private static AesEncryptor aesEncryptor;
    private static EncryptionTestClient client;

    public static void main(String[] args) throws IllegalBlockSizeException, BadPaddingException {
        initialize();

        byte[] encryptedMessage = aesEncryptor.encrypt(MESSAGE);
        String message = client.decryptMessage(encryptedMessage);
        System.out.println("Decrypted message: " + message);
    }

    private static void initialize() {
        RSA rsa = RSA.getInstance();
        KeyPair rsaKeyPair = rsa.generateKeys();

        try {
            //initialize RSA decryptor
            RsaUnwrapper rsaUnwrapper = new RsaUnwrapper(rsaKeyPair.getPrivate());

            //initialize client
            client = new EncryptionTestClient(rsaKeyPair.getPublic());
            byte[] encodedSymmetricKey = client.generateAndSendSecretKey();

            //initialize AES encryptor
            SecretKey symmetricKey = rsaUnwrapper.unwrap(encodedSymmetricKey);
            aesEncryptor = new AesEncryptor(symmetricKey);
        } catch (InvalidKeyException | NullPointerException | NoSuchAlgorithmException | IllegalBlockSizeException e) {
            log.log(WARNING, e.getMessage(), e);
        }
    }

}
