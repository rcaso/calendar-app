/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.contextholder;

import com.shava.calendar.presentation.view.UserInfo;
import java.io.Serializable;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author raul
 */
@Interceptor
@UserLogin
@Priority(Interceptor.Priority.APPLICATION)
public class UserLoginInterceptor implements Serializable{
    
    @Inject
    UserInfo userInfo;

    @AroundInvoke
    public Object logMethodEntry(InvocationContext ctx) throws Exception {
        System.out.println("Entering method: " + ctx.getMethod().getName());
        UserTrack userTrack = new UserTrack();
        userTrack.setUserId(userInfo.getUserId().longValue());
        userTrack.setUserName(userInfo.getUserName());
        ThreadLocalContextHolder.put(RegistryContextHolder.USER_TRACK,userTrack);
        return ctx.proceed();
    }
}
