/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.presentation.view;

import com.shava.calendar.authorization.boundary.UserResource;
import com.shava.calendar.authorization.entity.Login;
import com.shava.calendar.authorization.entity.UserCalendar;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import org.omnifaces.cdi.Param;
import org.omnifaces.util.Beans;
import org.omnifaces.util.Faces;
import static org.omnifaces.util.Faces.validationFailed;
import static org.omnifaces.util.Messages.addGlobalError;

/**
 *
 * @author raul
 */
@Model
public class LoginBean {
    
    @Inject
    private Login login;
    
    @Inject
    @Param(name = "continue") // Defined in @LoginToContinue of SecurityFormAuthenticationMechanism
    private boolean loginToContinue;
    
    @Inject
    SecurityContext securityContext;
    
    @Inject
    UserResource userResource;
    
    public void submit() {
        Credential credential = new UsernamePasswordCredential(login.getUsername(), new Password(login.getPassword()));

        // this will call our security configuration to authorize the user
        AuthenticationStatus status = securityContext.authenticate(
                Faces.getRequest(),
                Faces.getResponse(),
                withParams()
                        .credential(credential)
                        .newAuthentication(!loginToContinue)
                        .rememberMe(login.isRemember())
        );
        if (AuthenticationStatus.SUCCESS.equals(status)) {
            UserCalendar user = userResource.getUserWithEmail(login.getUsername()).get(0);
            UserInfo userInfo = Beans.getInstance(UserInfo.class,true);
            userInfo.setFullName(user.getFirstName()+" "+user.getLastName());
            userInfo.setUserId(user.getUserCalendarId());
            userInfo.setUserName(user.getEmail());
            Faces.redirect("dashboard.xhtml");
        } else if (AuthenticationStatus.SEND_FAILURE.equals(status)) {
            addGlobalError("auth.message.error.failure");
            validationFailed();
        }
    }

    /**
     * @return the login
     */
    public Login getLogin() {
        return login;
    }

    /**
     * @return the loginToContinue
     */
    public boolean isLoginToContinue() {
        return loginToContinue;
    }

    /**
     * @param loginToContinue the loginToContinue to set
     */
    public void setLoginToContinue(boolean loginToContinue) {
        this.loginToContinue = loginToContinue;
    }
    
}
