/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.exception;

import javax.ejb.ApplicationException;

/**
 *
 * @author raul
 */
@ApplicationException(rollback = true)
public class BusinessException extends  RuntimeException{
    
}
