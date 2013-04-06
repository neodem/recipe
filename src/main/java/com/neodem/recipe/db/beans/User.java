package com.neodem.recipe.db.beans;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.neodem.recipe.db.common.BaseVO;

public class User extends BaseVO {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String username;
	private String password;

	public int hashCode() {
		return new HashCodeBuilder(23, 13)
		.appendSuper(super.hashCode())
		.append(username)
		.append(password)
		.toHashCode();
	}

	public User() {
	}

	/**
	 * @return Returns the username.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username The username to set.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	
}
