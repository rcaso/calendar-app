/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.encrypt;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author raul
 */
//@HashAlgorithm
@ApplicationScoped
public class HashGenerator {

    private String algorithmName;

    public HashGenerator() {
        // EJB need this one.
    }

    public HashGenerator(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public String getHashText(String text) {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance(this.algorithmName);
            byte[] hash = messageDigest.digest(text.getBytes(StandardCharsets.UTF_8));
            String encoded = Base64.getEncoder().encodeToString(hash);
            return encoded;
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
}
