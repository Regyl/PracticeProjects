package com.deepspace.rsaaes.algorithm.rsa;

import lombok.extern.java.Log;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;

import static java.util.logging.Level.*;

@Log
public class RsaDecryptor extends RsaConstants {

    private Cipher decryptor;

    public RsaDecryptor(PrivateKey privateKey) throws InvalidKeyException {
        try {
            decryptor = Cipher.getInstance(ALGORITHM_NAME);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            log.log(WARNING, e.getMessage());
        }
        decryptor.init(Cipher.DECRYPT_MODE, privateKey);
    }

    public byte[] decrypt(byte[] payload) throws IllegalBlockSizeException, BadPaddingException {
        return decryptor.doFinal(payload);
    }
}
