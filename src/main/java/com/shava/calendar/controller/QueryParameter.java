/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.controller;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author raul
 */
public class QueryParameter {
    /** La parameters. */
	private Map<String,Object> parameters = null;

	/**
	 * Instancia un nuevo query parameter.
	 *
	 * @param name el name
	 * @param value el value
	 */
	private QueryParameter(String name, Object value) {
		this.parameters = new HashMap<>();
		this.parameters.put(name, value);
	}

	/**
	 * With.
	 *
	 * @param name el name
	 * @param value el value
	 * @return the query parameter
	 */
	public static QueryParameter with(String name, Object value) {
		return new QueryParameter(name, value);
	}

	/**
	 * And.
	 *
	 * @param name el name
	 * @param value el value
	 * @return the query parameter
	 */
	public QueryParameter and(String name, Object value) {
		this.parameters.put(name, value);
		return this;
	}

	/**
	 * Parameters.
	 *
	 * @return the map
	 */
	public Map<String,Object> parameters() {
		return this.parameters;
	}
}
