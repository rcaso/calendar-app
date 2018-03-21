/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.authorization.boundary;

import com.shava.calendar.authorization.entity.UserCalendar;
import com.shava.calendar.controller.CrudServiceController;
import com.shava.calendar.controller.QueryParameter;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author raul
 */
@Path("users")
@Stateless
public class UserResource {

    @Inject
    CrudServiceController crudService;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer createUser(UserCalendar user){
        Integer code = 0;
        List<UserCalendar> existUser = (List<UserCalendar>)crudService.findWithNamedQuery("UserCalendar.findByEmail", QueryParameter.with("email", user.getEmail()).parameters());
        if(existUser.isEmpty()){
            user.setCreatedBy(user.getEmail());
            crudService.create(user);
            code = user.getUserCalendarId();
        } 
        return code;
    }
    
    @GET
    @Path("/email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserCalendar> getUserWithEmail(@PathParam("email") String email){
       List<UserCalendar> existUser = (List<UserCalendar>)crudService.findWithNamedQuery("UserCalendar.findByEmail", QueryParameter.with("email", email).parameters());
       return existUser; 
    }
}
