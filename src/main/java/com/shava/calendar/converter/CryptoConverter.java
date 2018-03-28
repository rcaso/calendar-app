/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import com.shava.calendar.encrypt.EnCrypter;

/**
 *
 * @author raul
 */
@Converter
public class CryptoConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String value) {
        return EnCrypter.encrypt(value);
    }

    @Override
    public String convertToEntityAttribute(String value) {
        return EnCrypter.decrypt(value);
    }
    
}
