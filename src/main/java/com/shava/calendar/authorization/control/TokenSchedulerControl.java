/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.authorization.control;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

/**
 *
 * @author raul
 */
@Singleton
public class TokenSchedulerControl {

    @Inject
    TokenStoreControl tokenStore;

    @Schedule(dayOfWeek = "*", hour = "*", minute = "0", second = "0", persistent = false)
    public void hourly() {
        this.tokenStore.removeExpired();
    }
}
