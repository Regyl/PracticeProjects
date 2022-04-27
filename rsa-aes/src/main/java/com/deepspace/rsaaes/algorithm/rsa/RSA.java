package com.deepspace.rsaaes.algorithm.rsa;

import lombok.extern.java.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

import static java.util.logging.Level.*;

/**
 * Class written for using RSA encryption algorithm.
 * @author Regyl
 */
@Log
public class RSA extends RsaConstants {

    private static final int DEFAULT_KEY_SIZE = 4096;
    private static KeyPairGenerator generator = null;
    private static final String PATH_TO_PUBLIC_KEY = "rsa.pub";
    private static RSA instance;

    static {
        try {
            generator = KeyPairGenerator.getInstance(ALGORITHM_NAME);
            generator.initialize(DEFAULT_KEY_SIZE);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * It's singleton, therefore there is a static method for getting instance
     * @return instance of RSA class
     */
    public static RSA getInstance() {
        if(Objects.isNull(instance)) {
            instance = new RSA();
        }
        return instance;
    }

    public KeyPair generateKeys() {
        KeyPair keyPair = generator.generateKeyPair();
        writePublicKey(keyPair.getPublic());
        return keyPair;
    }

    public PublicKey readPublicKey() {
        try {
            File publicKeyFile = new File(PATH_TO_PUBLIC_KEY);
            byte[] publicKeyBytes = Files.readAllBytes(publicKeyFile.toPath());
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            return keyFactory.generatePublic(publicKeySpec);
        } catch (NoSuchAlgorithmException | IOException | InvalidKeySpecException e) {
            log.log(WARNING, e.getMessage());
            return null;
        }
    }

    private void writePublicKey(PublicKey key) {
        try (FileOutputStream fos = new FileOutputStream(PATH_TO_PUBLIC_KEY)) {
            fos.write(key.getEncoded());
        } catch (FileNotFoundException e) {
            log.log(WARNING, e.getMessage());
        } catch (IOException e) {
            log.log(WARNING, e.getMessage(), e);
        }
    }

    private RSA() {}

}
