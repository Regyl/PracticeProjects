package com.deepspace.rsaaes;

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
public class RsaAes {

    private static KeyPair rsaKeyPair;
    private static RsaDecryptor decryptor;
    private static SecretKey symmetricKey;

    public static void main(String[] args) {
        RSA rsa = RSA.getInstance();
        rsaKeyPair = rsa.generateKeys();

        try {
            decryptor = new RsaDecryptor(rsaKeyPair.getPrivate()); //initialize

            EncryptionTestClient client = new EncryptionTestClient(rsaKeyPair.getPublic()); //initialize client and receive AES key
            byte[] encodedSymmetricKey = client.generateAndSendSecretKey();
            symmetricKey = new SecretKeySpec(decryptor.decrypt(encodedSymmetricKey), "AES");


        } catch (InvalidKeyException | NullPointerException | IllegalBlockSizeException | BadPaddingException e) {
            log.log(WARNING, e.getMessage());
        }

    }

}
