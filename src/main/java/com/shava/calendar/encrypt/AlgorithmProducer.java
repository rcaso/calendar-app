/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.encrypt;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.InjectionPoint;


/**
 *
 * @author raul
 */
public class AlgorithmProducer {
    
    @Produces
    @HashAlgorithm
    public HashGenerator produceHashGenerator(InjectionPoint ip) {
        Annotated annotated = ip.getAnnotated();
        HashAlgorithm hashAlgorithm = annotated.getAnnotation(HashAlgorithm.class);
        HashGenerator hashGenerator = new HashGenerator(hashAlgorithm.algorithm().getAlgorithmName());
        return hashGenerator;
    }
}
