/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.provider;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author raul
 */
@Provider
public class JacksonConfig implements ContextResolver<ObjectMapper> {
    
    private final ObjectMapper objectMapper;
    
    public JacksonConfig() throws Exception {

    objectMapper = new ObjectMapper()
                .disable( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS )
                .disable( SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS )
                .setSerializationInclusion( JsonInclude.Include.NON_NULL )
                .registerModule( new JavaTimeModule () );

}

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return objectMapper;
    }
    
}
