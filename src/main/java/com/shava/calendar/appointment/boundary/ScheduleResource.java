/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.appointment.boundary;

import com.shava.calendar.appointment.control.SchedulerProcess;
import com.shava.calendar.appointment.entity.Appointment;
import com.shava.calendar.appointment.entity.Schedule;
import com.shava.calendar.controller.CrudServiceController;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author raul
 */
@Path("schedules")
@Stateless
public class ScheduleResource {

    @Inject
    SchedulerProcess schedulerProcess;
    
    @Inject
    CrudServiceController crudService;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveAppointments(Appointment appointment){
        schedulerProcess.saveAppointments(appointment);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Schedule> getAllSchedules(@QueryParam("user") Integer user){
        return schedulerProcess.getAllSchedules(user);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateSchedule(Schedule schedule){
        crudService.update(schedule);
    }
    
}
