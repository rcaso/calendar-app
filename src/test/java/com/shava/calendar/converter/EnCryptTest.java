/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.converter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import com.shava.calendar.encrypt.EnCrypter;

/**
 *
 * @author raul
 */
public class EnCryptTest {
    
    public EnCryptTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of encrypt method, of class EnCrypt.
     */
    @org.junit.Test
    public void testEncrypt() {
        System.out.println("encrypt");
        String value = "sistemas10";
        String expResult = "";
        String result = EnCrypter.encrypt(value);
        System.out.println("Value "+value+" encrypted is "+result);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of decrypt method, of class EnCrypt.
     */
    @org.junit.Test
    public void testDecrypt() {
        System.out.println("decrypt");
        String value = "";
        String expResult = "";
        String result = EnCrypter.decrypt(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
