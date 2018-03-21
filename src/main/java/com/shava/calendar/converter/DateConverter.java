/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.converter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author raul
 */
public interface DateConverter {
    static Date converToDate(LocalDateTime date){
        return Date.from(date.atZone(ZoneId.of(ZoneTimeLima.GMT_5)).toInstant());
    }
    
    static LocalDateTime convertToLocalDateTime(Date value){
        return Instant.ofEpochMilli(value.getTime()).atZone(ZoneId.of(ZoneTimeLima.GMT_5)).toLocalDateTime();
    }
    
    static Date convertToDate(LocalDate date){
        return Date.from(date.atStartOfDay().atZone(ZoneId.of(ZoneTimeLima.GMT_5)).toInstant());
    }
    
    static LocalDate convertToLocalDate(Date value){
        return Instant.ofEpochMilli(value.getTime()).atZone(ZoneId.of(ZoneTimeLima.GMT_5)).toLocalDate();
    }
}
