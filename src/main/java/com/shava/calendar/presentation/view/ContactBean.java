/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.presentation.view;

import com.shava.calendar.appointment.boundary.ContactResource;
import com.shava.calendar.appointment.entity.Contact;
import com.shava.calendar.contextholder.UserLogin;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *
 * @author raul
 */
@ViewScoped
@Named
public class ContactBean implements Serializable{
    
    @Inject
    ContactResource contactResource;
    
    @Inject
    UserInfo userInfo;
    
    private Contact contact;
    
    private List<Contact> usersContacts;
    
    @PostConstruct
    public void init(){
        contact = new Contact();
        
    }
    
    public void loadContacts(){
        usersContacts = contactResource.getAllContacts(userInfo.getUserId());
    }
    
    @UserLogin
    public void saveContact(){
        contact.setUserCalendarId(userInfo.getUserId());
        contactResource.updateContact(contact);
        loadContacts();
        PrimeFaces.current().ajax().update("contactForm:contactsTable","contactForm:contactModal");
    }
    
    public void newContact(){
        contact = new Contact();
    }
    

    /**
     * @return the contact
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /**
     * @return the usersContacts
     */
    public List<Contact> getUsersContacts() {
        return usersContacts;
    }

    /**
     * @param usersContacts the usersContacts to set
     */
    public void setUsersContacts(List<Contact> usersContacts) {
        this.usersContacts = usersContacts;
    }
    
    
}
