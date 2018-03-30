/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.appointment.entity;

import com.shava.calendar.entity.BaseEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author raul
 */
@Entity
@Table(name = "schedule")
@NamedQueries({
    @NamedQuery(name = "Schedule.findAll", query = "SELECT s FROM Schedule s where s.userCalendarId = :user order by s.createdDate ")})
public class Schedule extends BaseEntity implements Serializable {
    
    public static final String FIND_ALL_SCHEDULES ="Schedule.findAll";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "schedule_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "schedule_id_seq", sequenceName = "schedule_schedule_id_seq", allocationSize = 1)
    @Column(name = "schedule_id")
    private Integer scheduleId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "description")
    private String description;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "begin_date")
    private LocalDateTime beginDate;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_date")
    private LocalDateTime endDate;
    
    @Column(name = "ind_notification")
    @Enumerated(EnumType.STRING)
    private ActivedType indNotification;
    
    @Column(name = "notification_type")
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
    
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_calendar_id")
    private Integer userCalendarId;
    
    @Column(name = "contact_id")
    @NotNull
    private Integer contactId;

    public Schedule() {
    }

    public Schedule(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Schedule(Integer scheduleId, String description, LocalDateTime beginDate, LocalDateTime endDate, int userCalendarId) {
        this.scheduleId = scheduleId;
        this.description = description;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.userCalendarId = userCalendarId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDateTime beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public ActivedType getIndNotification() {
        return indNotification;
    }

    public void setIndNotification(ActivedType indNotification) {
        this.indNotification = indNotification;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public Integer getUserCalendarId() {
        return userCalendarId;
    }

    public void setUserCalendarId(Integer userCalendarId) {
        this.userCalendarId = userCalendarId;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scheduleId != null ? scheduleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Schedule)) {
            return false;
        }
        Schedule other = (Schedule) object;
        if ((this.scheduleId == null && other.scheduleId != null) || (this.scheduleId != null && !this.scheduleId.equals(other.scheduleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.shava.calendar.appointment.entity.Schedule[ scheduleId=" + scheduleId + " ]";
    }
    
}
