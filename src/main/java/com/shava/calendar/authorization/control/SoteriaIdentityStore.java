/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.authorization.control;

import com.shava.calendar.authorization.InvalidCredentialException;
import com.shava.calendar.authorization.InvalidPasswordException;
import com.shava.calendar.authorization.InvalidUsernameException;
import com.shava.calendar.authorization.entity.UserCalendar;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.CallerOnlyCredential;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import static javax.security.enterprise.identitystore.CredentialValidationResult.NOT_VALIDATED_RESULT;
import javax.security.enterprise.identitystore.IdentityStore;

/**
 *
 * @author raul
 */
@ApplicationScoped
public class SoteriaIdentityStore implements IdentityStore {

    @Inject
    UserCalendarStore userStore;

    @Override
    public CredentialValidationResult validate(Credential credential) {
        try {

            // check if the credential was UsernamePasswordCredential
            if (credential instanceof UsernamePasswordCredential) {
                String username = ((UsernamePasswordCredential) credential).getCaller();
                String password = ((UsernamePasswordCredential) credential).getPasswordAsString();

                return validate(userStore.getByUsernameAndPassword(username, password));
            } else if (credential instanceof CallerOnlyCredential) {
                // check if the credential was CallerOnlyCredential
                String username = ((CallerOnlyCredential) credential).getCaller();

                return validate(userStore.getByEmail(username).orElseThrow(InvalidCredentialException::new));
            }

        } catch (InvalidCredentialException | InvalidPasswordException | InvalidUsernameException e) {
            return INVALID_RESULT;
        }
        return NOT_VALIDATED_RESULT;
    }

    // before return the CredentialValidationResult, check if the account is active or not
    private CredentialValidationResult validate(UserCalendar account) throws InvalidCredentialException {
//        if (!account.isActive()) {
//            throw new AccountNotVerifiedException();
//        }
        if (account == null) {
            throw new InvalidCredentialException();
        }
        return new CredentialValidationResult(account.getEmail());
    }

}
