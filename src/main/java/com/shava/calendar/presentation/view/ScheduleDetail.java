/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.presentation.view;

import java.io.Serializable;

/**
 *
 * @author raul
 */
public class ScheduleDetail implements Serializable{
    private Integer scheduleId;
    private Integer contactId;
    private String activedNotification;
    private String notificationType;

    /**
     * @return the scheduleId
     */
    public Integer getScheduleId() {
        return scheduleId;
    }

    /**
     * @param scheduleId the scheduleId to set
     */
    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    /**
     * @return the contactId
     */
    public Integer getContactId() {
        return contactId;
    }

    /**
     * @param contactId the contactId to set
     */
    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    /**
     * @return the activedNotification
     */
    public String getActivedNotification() {
        return activedNotification;
    }

    /**
     * @param activedNotification the activedNotification to set
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
}
