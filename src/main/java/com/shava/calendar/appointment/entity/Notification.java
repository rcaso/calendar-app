/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.appointment.entity;

import com.shava.calendar.entity.BaseEntity;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author raul
 */
@Entity
@Table(name = "notification")
@NamedQueries({
    @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n")})
public class Notification extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "notification_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "notification_id_seq", sequenceName = "notification_notification_id_seq", allocationSize = 1)
    @Column(name = "notification_id")
    private Integer notificationId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "before_time")
    private Integer beforeTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "unit_type")
    private Integer unitType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_calendar_id")
    private Integer userCalendarId;

    public Notification() {
    }

    public Notification(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public Notification(Integer notificationId, int beforeTime, int unitType, int userCalendarId) {
        this.notificationId = notificationId;
        this.beforeTime = beforeTime;
        this.unitType = unitType;
        this.userCalendarId = userCalendarId;
    }

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public int getBeforeTime() {
        return beforeTime;
    }

    public void setBeforeTime(int beforeTime) {
        this.beforeTime = beforeTime;
    }

    public int getUnitType() {
        return unitType;
    }

    public void setUnitType(int unitType) {
        this.unitType = unitType;
    }

    public int getUserCalendarId() {
        return userCalendarId;
    }

    public void setUserCalendarId(int userCalendarId) {
        this.userCalendarId = userCalendarId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notificationId != null ? notificationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.notificationId == null && other.notificationId != null) || (this.notificationId != null && !this.notificationId.equals(other.notificationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.shava.calendar.appointment.entity.Notification[ notificationId=" + notificationId + " ]";
    }
    
}
