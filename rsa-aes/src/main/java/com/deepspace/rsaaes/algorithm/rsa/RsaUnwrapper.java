package com.deepspace.rsaaes.algorithm.rsa;

import lombok.extern.java.Log;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;

import static java.util.logging.Level.*;

@Log
public class RsaUnwrapper extends RsaConstants {

    private Cipher unwrapper;

    public RsaUnwrapper(PrivateKey privateKey) throws InvalidKeyException {
        try {
            unwrapper = Cipher.getInstance(ALGORITHM_NAME);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            log.log(WARNING, e.getMessage());
        }
        unwrapper.init(Cipher.UNWRAP_MODE, privateKey);
    }

    /**
     *
     * @param key
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public SecretKey unwrap(byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        return (SecretKey) unwrapper.unwrap(key, "AES", Cipher.SECRET_KEY);
    }
}
