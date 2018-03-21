/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.listener;

import com.shava.calendar.contextholder.RegistryContextHolder;
import com.shava.calendar.contextholder.ThreadLocalContextHolder;
import com.shava.calendar.contextholder.UserTrack;
import com.shava.calendar.entity.BaseEntity;
import java.time.LocalDateTime;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author raul
 */
public class AuditListener {
    
    @PrePersist
    public void onCreated(Object baseEntity){
        if(baseEntity instanceof BaseEntity){
            BaseEntity entity = (BaseEntity)baseEntity;
            entity.setCreatedDate(LocalDateTime.now());
            UserTrack userTrack = (UserTrack)ThreadLocalContextHolder.get(RegistryContextHolder.USER_TRACK);
            if(userTrack != null){
                entity.setCreatedBy(userTrack.getUserName());
            }
        }
        
    }
    
    @PreUpdate
    public void onChanged(Object baseEntity){
        if(baseEntity instanceof BaseEntity){
            BaseEntity entity = (BaseEntity)baseEntity;
            entity.setUpdatedDate(LocalDateTime.now());
            UserTrack userTrack = (UserTrack)ThreadLocalContextHolder.get(RegistryContextHolder.USER_TRACK);
            if(userTrack != null){
                entity.setUpdatedBy(userTrack.getUserName());
            }
        }
    }
}
