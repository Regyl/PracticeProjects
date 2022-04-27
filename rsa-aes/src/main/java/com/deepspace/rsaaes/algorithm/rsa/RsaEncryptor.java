package com.deepspace.rsaaes.algorithm.rsa;

import lombok.extern.java.Log;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import static java.util.logging.Level.WARNING;

@Log
public class RsaEncryptor extends RsaConstants {

    private Cipher encryptor;

    public RsaEncryptor(PublicKey publicKey) throws InvalidKeyException {
        try {
            encryptor = Cipher.getInstance(ALGORITHM_NAME);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
            log.log(WARNING, e.getMessage());
        }
        encryptor.init(Cipher.ENCRYPT_MODE, publicKey);
    }

    public byte[] encrypt(String payload) throws IllegalBlockSizeException, BadPaddingException {
        return encryptor.doFinal(payload.getBytes(CHARSET));
    }
}
