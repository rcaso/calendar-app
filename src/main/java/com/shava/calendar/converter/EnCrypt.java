/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.converter;

import java.security.Key;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author raul
 */
public interface EnCrypt {
    static final String ALGORITHM = "AES/ECB/PKCS5Padding";
    static final byte[] KEY = "secretKeyCrypto1".getBytes();
    
    static String encrypt(String value){
        Key key = new SecretKeySpec(KEY, "AES");
      try {
         Cipher c = Cipher.getInstance(ALGORITHM);
         c.init(Cipher.ENCRYPT_MODE, key);
         return Base64.getEncoder().encodeToString(c.doFinal(value.getBytes()));
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
    }
    
    static String decrypt(String value){
        Key key = new SecretKeySpec(KEY, "AES");
      try {
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);
        return new String(c.doFinal(Base64.getDecoder().decode(value)));
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
}
