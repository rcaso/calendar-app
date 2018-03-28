/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.encrypt;

/**
 *
 * @author raul
 */
public enum Algorithm {
    SHA256 ("SHA-256"),
    SHA512 ("SHA-512");
    
    private final String name;
    
    private Algorithm(String name) {
        this.name = name;
    }
    
    public String getAlgorithmName() {
        return this.name;
    }

}
