/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.authorization.control;

import com.shava.calendar.authorization.InvalidUsernameException;
import com.shava.calendar.authorization.entity.Token;
import com.shava.calendar.authorization.entity.TokenType;
import com.shava.calendar.authorization.entity.UserCalendar;
import com.shava.calendar.controller.CrudServiceController;
import com.shava.calendar.encrypt.Algorithm;
import com.shava.calendar.encrypt.HashAlgorithm;
import com.shava.calendar.encrypt.HashGenerator;
import java.time.Instant;
import static java.time.Instant.now;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.UUID.randomUUID;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author raul
 */
@ApplicationScoped
public class TokenStoreControl {

    @Inject
    CrudServiceController crudService;
            
    @Inject
    @HashAlgorithm(algorithm = Algorithm.SHA256)
    HashGenerator hash;
    
    @Inject
    UserCalendarStore userStore;
    

    public String generate(final String username, final String ipAddress, final String description,
            final TokenType tokenType) {
        String rawToken = randomUUID().toString();
        Instant expiration = now().plus(14, DAYS);
        save(rawToken, username, ipAddress, description, tokenType, expiration);
        return rawToken;
    }

    public void save(final String rawToken, final String username, final String ipAddress,
            final String description, final TokenType tokenType, final Instant expiration) {
        UserCalendar account = userStore.getByEmail(username).orElseThrow(InvalidUsernameException::new);
        Token token = new Token();
        token.setTokenHash(this.hash.getHashText(rawToken));
        token.setExpiration(expiration);
        token.setDescription(description);
        token.setTokenType(tokenType);
        token.setIpAddress(ipAddress);
        token.setAccount(account);
//        account.addToken(token);
        crudService.update(token);
    }

    public void remove(String token) {
        crudService.getEntityManager().createNamedQuery(Token.REMOVE_TOKEN)
                .setParameter("tokenHash", token).executeUpdate();
    }

    public void removeExpired() {
        crudService.getEntityManager().createNamedQuery(Token.REMOVE_EXPIRED_TOKEN).executeUpdate();
    }
}
