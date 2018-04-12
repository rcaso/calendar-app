/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.presentation.view;

import com.shava.calendar.appointment.boundary.ContactResource;
import com.shava.calendar.appointment.boundary.ScheduleResource;
import com.shava.calendar.appointment.entity.Appointment;
import com.shava.calendar.appointment.entity.Contact;
import com.shava.calendar.contextholder.UserLogin;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Faces;

/**
 *
 * @author raul
 */
@Named
@ViewScoped
public class AppointmentBean implements Serializable {

    @Inject
    ScheduleResource scheduleResource;

    @Inject
    ContactResource contactResource;

    @Inject
    UserInfo userInfo;

    private Appointment appointment;

    private LocalDate date;

    private String beginHour;

    private String endHour;

    private List<String> hours;

    public List<Contact> completeContact(String name) {
        return contactResource.getContactsByName(userInfo.getUserId(), name);
    }
    
    @UserLogin
    public void submit(){
        appointment.setUser(userInfo.getUserId());
        scheduleResource.saveAppointments(appointment);
        Faces.redirect("dashboard.xhtml");
    }

    public void registerSchedule() {
        LocalDateTime begin = LocalDateTime.of(getDate(), LocalTime.parse(getBeginHour() + ":00"));
        LocalDateTime end = LocalDateTime.of(getDate(), LocalTime.parse(getEndHour() + ":00"));
        HashMap<String, LocalDateTime> range = new HashMap<>();
        range.put("beginDate", begin);
        range.put("endDate", end);
        appointment.getDates().add(range);
    }

    @PostConstruct
    public void init() {
        appointment = new Appointment();
        appointment.setDates(new ArrayList<>());
        hours = new ArrayList<>();
        int gapInMinutes = 30;  // Define your span-of-time.
        int loops = ((int) Duration.ofHours(24).toMinutes() / gapInMinutes);
        List<LocalTime> times = new ArrayList<>(loops);
        LocalTime time = LocalTime.MIN;  // '00:00'
        for (int i = 1; i <= loops; i++) {
            hours.add(time.toString());
            // Set up next loop.
            time = time.plusMinutes(gapInMinutes);
        }
    }

    /**
     * @return the appointment
     */
    public Appointment getAppointment() {
        return appointment;
    }

    /**
     * @param appointment the appointment to set
     */
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @return the beginHour
     */
    public String getBeginHour() {
        return beginHour;
    }

    /**
     * @param beginHour the beginHour to set
     */
    public void setBeginHour(String beginHour) {
        this.beginHour = beginHour;
    }

    /**
     * @return the endHour
     */
    public String getEndHour() {
        return endHour;
    }

    /**
     * @param endHour the endHour to set
     */
    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    /**
     * @return the hours
     */
    public List<String> getHours() {
        return hours;
    }

    /**
     * @param hours the hours to set
     */
    public void setHours(List<String> hours) {
        this.hours = hours;
    }

}
