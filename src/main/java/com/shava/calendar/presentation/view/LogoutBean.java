/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.presentation.view;

import java.io.IOException;
import javax.enterprise.inject.Model;
import javax.servlet.ServletException;
import org.omnifaces.util.Faces;
import static org.omnifaces.util.Faces.invalidateSession;

/**
 *
 * @author raul
 */
@Model
public class LogoutBean {

    public void submit() throws ServletException, IOException {
        Faces.logout();
        invalidateSession();
        Faces.redirect("login.xhtml");
    }
}
