/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.authorization.entity;

import com.shava.calendar.converter.EnCrypt;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author raul
 */
@Converter
public class CryptoConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String value) {
        return EnCrypt.encrypt(value);
    }

    @Override
    public String convertToEntityAttribute(String value) {
        return EnCrypt.decrypt(value);
    }
    
}
