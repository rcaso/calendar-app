/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.contextholder;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author raul
 */
public class UserTrack implements Serializable{
    /** La Constante serialVersionUID. */
	private static final long serialVersionUID = 5057887879503553647L;
	
	/** La user name. */
	private String userName;
        
        private Long userId;
	
	/** La ip address. */
	private String ipAddress;
	
	
	/** La menu option. */
	private String menuOption;
	
	/** La roles. */
	private List<String> roles;

	/**
	 * Obtiene user name.
	 *
	 * @return user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Establece el user name.
	 *
	 * @param userName el new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Obtiene ip address.
	 *
	 * @return ip address
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * Establece el ip address.
	 *
	 * @param ipAddress el new ip address
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * Obtiene menu option.
	 *
	 * @return menu option
	 */
	public String getMenuOption() {
		return menuOption;
	}

	/**
	 * Establece el menu option.
	 *
	 * @param menuOption el new menu option
	 */
	public void setMenuOption(String menuOption) {
		this.menuOption = menuOption;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserTrack [userName=" + userName + ", ipAddress=" + ipAddress + ", menuOption=" + menuOption + "]";
	}

	/**
	 * Obtiene roles.
	 *
	 * @return roles
	 */
	public List<String> getRoles() {
		return roles;
	}

	/**
	 * Establece el roles.
	 *
	 * @param roles el new roles
	 */
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
