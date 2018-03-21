/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.entitymanager;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author raul
 */
public class ProducerDataBase {

    @Produces
    @DbApplication
    @PersistenceContext(unitName = "calendar_ds")
    private EntityManager em;
}
