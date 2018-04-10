/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.presentation.view;

import com.shava.calendar.authorization.boundary.UserResource;
import com.shava.calendar.authorization.entity.UserCalendar;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.Faces;
import static org.omnifaces.util.Faces.validationFailed;
import static org.omnifaces.util.Messages.addGlobalError;

/**
 *
 * @author raul
 */
@Named
@ViewScoped
public class RegisterBean implements Serializable {

    @Inject
    UserResource userResource;

    private UserCalendar userCalendar;
    
    @PostConstruct
    public void init(){
        userCalendar = new UserCalendar();
    }

    public void registerUser() {
        Integer value = userResource.createUser(userCalendar);
        if (value > 0) {
            Faces.redirect("login.xhtml");
        } else {
            addGlobalError("Error al registrar usuario el email ya esta registrado");
            validationFailed();
        }
    }

    /**
     * @return the userCalendar
     */
    public UserCalendar getUserCalendar() {
        return userCalendar;
    }

    /**
     * @param userCalendar the userCalendar to set
     */
    public void setUserCalendar(UserCalendar userCalendar) {
        this.userCalendar = userCalendar;
    }

}
