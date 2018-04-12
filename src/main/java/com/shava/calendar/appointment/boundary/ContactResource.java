/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.appointment.boundary;

import com.shava.calendar.appointment.entity.Contact;
import com.shava.calendar.controller.CrudServiceController;
import com.shava.calendar.controller.QueryParameter;
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
@Path("/contacts")
@Stateless
public class ContactResource {

    @Inject
    CrudServiceController crudService;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer createContact(Contact contact){
        contact = crudService.create(contact);
        return contact.getContactId();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Contact> getContactsByName(@QueryParam("user") Integer user,@QueryParam("name") String name){
        return (List<Contact>)crudService.findWithNamedQuery(Contact.FIND_BY_NAME,QueryParameter.with("name","%"+name+"%")
                .and("user",user).parameters());
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateContact(Contact contact){
        crudService.update(contact);
    }
    
}
