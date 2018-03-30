/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.appointment.entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author raul
 */
public class Appointment {
    private Integer contact;
    private Integer user;
    private String description;
    private String activedNotification;
    private String notificationType;
    private List<HashMap<String,LocalDateTime>> dates;

    /**
     * @return the contact
     */
    public Integer getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(Integer contact) {
        this.contact = contact;
    }

    /**
     * @return the user
     */
    public Integer getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(Integer user) {
        this.user = user;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the actived
     */
    public String getActivedNotification() {
        return activedNotification;
    }

    /**
     * @param activedNotification the actived to set
     */
    public void setActivedNotification(String activedNotification) {
        this.activedNotification = activedNotification;
    }

    /**
     * @return the notificationType
     */
    public String getNotificationType() {
        return notificationType;
    }

    /**
     * @param notificationType the notificationType to set
     */
    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    /**
     * @return the dates
     */
    public List<HashMap<String,LocalDateTime>> getDates() {
        return dates;
    }

    /**
     * @param dates the dates to set
     */
    public void setDates(List<HashMap<String,LocalDateTime>> dates) {
        this.dates = dates;
    }
    
}
