/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.appointment.control;

import com.shava.calendar.appointment.entity.ActivedType;
import com.shava.calendar.appointment.entity.Appointment;
import com.shava.calendar.appointment.entity.NotificationType;
import com.shava.calendar.appointment.entity.Schedule;
import com.shava.calendar.controller.CrudServiceController;
import com.shava.calendar.controller.QueryParameter;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author raul
 */
@ApplicationScoped
public class SchedulerProcess {
    
    @Inject
    CrudServiceController crudService;
    
    public void saveAppointments(Appointment appointments){
        appointments.getDates().forEach(appointment ->{
        Schedule schedule = new Schedule();
        schedule.setContactId(appointments.getContact());
        schedule.setUserCalendarId(appointments.getUser());
        schedule.setDescription(appointments.getDescription());
        schedule.setIndNotification(ActivedType.valueOf(appointments.getActivedNotification()));
        if(appointments.getNotificationType()!=null){
            schedule.setNotificationType(NotificationType.valueOf(appointments.getNotificationType()));
        }
        schedule.setBeginDate(appointment.get("beginDate"));
        schedule.setEndDate(appointment.get("endDate"));
        schedule.setCreatedBy("feve18@gmail.com");
        crudService.create(schedule);
    });
    }
    
    public List<Schedule> getAllSchedules(Integer user){
        return crudService.findWithNamedQuery(Schedule.FIND_ALL_SCHEDULES,QueryParameter.with("user", user).parameters());
    }
    
    
}
