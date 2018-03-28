/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.authorization.control;

import com.shava.calendar.authorization.InvalidPasswordException;
import com.shava.calendar.authorization.InvalidUsernameException;
import com.shava.calendar.authorization.entity.TokenType;
import com.shava.calendar.authorization.entity.UserCalendar;
import com.shava.calendar.controller.CrudServiceController;
import com.shava.calendar.controller.QueryParameter;
import com.shava.calendar.encrypt.Algorithm;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import com.shava.calendar.encrypt.HashAlgorithm;
import com.shava.calendar.encrypt.HashGenerator;
import java.util.List;

/**
 *
 * @author raul
 */
@ApplicationScoped
public class UserCalendarStore {

    @Inject
    CrudServiceController crudService;

    @Inject
    @HashAlgorithm(algorithm = Algorithm.SHA256)
    HashGenerator tokenHash;

    @Inject
    @HashAlgorithm
    HashGenerator passwordHash;

    public UserCalendar createUser(UserCalendar user) {
        List<UserCalendar> existUser = (List<UserCalendar>) crudService.findWithNamedQuery(UserCalendar.FIND_BY_EMAIL, QueryParameter.with("email", user.getEmail()).parameters());
        if (existUser.isEmpty()) {
            user.setCreatedBy(user.getEmail());
            String securedPassword = this.passwordHash.getHashText(user.getUserPassword());
            user.setUserPassword(securedPassword);
            return crudService.create(user);
        } else {
            return null;
        }
    }

    public UserCalendar getByUsernameAndPassword(String username, String password) {
        UserCalendar user = getByEmail(username).orElseThrow(InvalidUsernameException::new);
        String hashesPassword = this.passwordHash.getHashText(password);
        if (!hashesPassword.equals(user.getUserPassword())) {
            throw new InvalidPasswordException();
        }
        return user;
    }

    public Optional<UserCalendar> getByLoginToken(String loginToken, TokenType tokenType) {
        try {
            return Optional.of(crudService.getEntityManager().createNamedQuery(UserCalendar.FIND_BY_TOKEN, UserCalendar.class)
                    .setParameter("tokenHash", tokenHash.getHashText(loginToken))
                    .setParameter("tokenType", tokenType)
                    .getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    public Optional<UserCalendar> getByEmail(String email) {
        try {
            return Optional.of(crudService.getEntityManager().createNamedQuery(UserCalendar.FIND_BY_EMAIL, UserCalendar.class)
                    .setParameter("email", email)
                    .getSingleResult());
        } catch (NoResultException | NonUniqueResultException ex) {
            return Optional.empty();
        }
    }

}
