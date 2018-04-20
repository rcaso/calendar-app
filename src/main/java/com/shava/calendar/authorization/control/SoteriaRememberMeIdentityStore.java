/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.authorization.control;

import static com.shava.calendar.authorization.entity.TokenType.REMEMBER_ME;
import static org.omnifaces.util.Servlets.getRemoteAddr;
import com.shava.calendar.authorization.entity.UserCalendar;
import com.shava.calendar.presentation.view.UserInfo;
import java.util.Optional;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.CallerPrincipal;
import javax.security.enterprise.credential.RememberMeCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import javax.security.enterprise.identitystore.RememberMeIdentityStore;
import javax.servlet.http.HttpServletRequest;
import org.omnifaces.util.Beans;

/**
 *
 * @author raul
 */
@ApplicationScoped
public class SoteriaRememberMeIdentityStore implements RememberMeIdentityStore{

    @Inject
    HttpServletRequest request;

    @Inject
    UserCalendarStore userStore;

    @Inject
    TokenStoreControl tokenStore;

    @Override
    public CredentialValidationResult validate(RememberMeCredential rmc) {
        Optional<UserCalendar> account = this.userStore.getByLoginToken(rmc.getToken(), REMEMBER_ME);

        if (account.isPresent()) {
            //create userInfo
            UserCalendar user = account.get();
            UserInfo userInfo = Beans.getInstance(UserInfo.class,true);
            userInfo.setFullName(user.getFirstName()+" "+user.getLastName());
            userInfo.setUserId(user.getUserCalendarId());
            userInfo.setUserName(user.getEmail());
            return new CredentialValidationResult(new CallerPrincipal(user.getEmail()));
        } else {
            return INVALID_RESULT;
        }
    }

    @Override
    public String generateLoginToken(CallerPrincipal cp, Set<String> set) {
        //getRemoteAddr() was method that come with Omnifaces dependency
        return this.tokenStore.generate(cp.getName(),getRemoteAddr(request), getDescription(), REMEMBER_ME);
    }

    @Override
    public void removeLoginToken(String loginToken) {
        this.tokenStore.remove(loginToken);
    }

    private String getDescription() {
        return "Remember me session: " + this.request.getHeader("User-Agent");
    }
}
