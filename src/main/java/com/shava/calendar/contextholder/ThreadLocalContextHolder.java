/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.contextholder;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author raul
 */
public class ThreadLocalContextHolder {
    /** La Constante THREAD_WITH_CONTEXT. */
	private static final ThreadLocal<Map<String,Object>> THREAD_WITH_CONTEXT = new ThreadLocal<Map<String,Object>>();
	
	/**
	 * Put.
	 *
	 * @param key el key
	 * @param payload el payload
	 */
	public static void put(String key, Object payload) {
        if(THREAD_WITH_CONTEXT.get() == null){
            THREAD_WITH_CONTEXT.set(new HashMap<String, Object>());
        }
        THREAD_WITH_CONTEXT.get().put(key, payload);
    }

    /**
     * Gets the.
     *
     * @param key el key
     * @return the object
     */
    public static Object get(String key) {
        if(checkInitialited()){
            return THREAD_WITH_CONTEXT.get().get(key);
        } else {
            return null;
        }
        
    }

    /**
     * Cleanup thread.
     */
    public static void cleanupThread(){
        THREAD_WITH_CONTEXT.remove();
    }
    
    /**
     * Check initialited.
     *
     * @return true, en caso de exito
     */
    public static boolean checkInitialited() {
    	return THREAD_WITH_CONTEXT.get() != null;
    }

	/**
	 * Instancia un nuevo thread local context holder.
	 */
	private ThreadLocalContextHolder() {
		// TODO Auto-generated constructor stub
	}
}
