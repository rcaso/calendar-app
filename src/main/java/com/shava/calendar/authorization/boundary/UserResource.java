/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.authorization.boundary;

import com.shava.calendar.authorization.control.UserCalendarStore;
import com.shava.calendar.authorization.entity.Login;
import com.shava.calendar.authorization.entity.UserCalendar;
import com.shava.calendar.controller.CrudServiceController;
import com.shava.calendar.controller.QueryParameter;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
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
    
    @Inject
    UserCalendarStore userStore;
    
    @Context
    HttpServletRequest request;
    
    @Context
    HttpServletResponse response;
    
    @Inject
    SecurityContext securityContext;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer createUser(UserCalendar user){
        Integer code = 0;
        UserCalendar userCreated = userStore.createUser(user);
        if(userCreated != null){
             code = userCreated.getUserCalendarId();
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
    
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public String authenticate(Login login){
         // credential that want to be validate was UsernamePasswordCredential
        Credential credential = new UsernamePasswordCredential(login.getUsername(), new Password(login.getPassword()));

        // this will call our security configuration to authorize the user
        AuthenticationStatus status = securityContext.authenticate(
                request,
                response,
                withParams()
                        .credential(credential)
                        .newAuthentication(true)
                        .rememberMe(login.isRemember())
        );
        
        return status.toString();
        
    }
    
    
    
    
}
