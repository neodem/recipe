package com.neodem.recipe.web.beans;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserLoginToken extends LoginToken {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static Log _log = LogFactory.getLog(UserLoginToken.class.getName());
    private String username;

    public UserLoginToken() {
        super();
    }
    
    public UserLoginToken(String username) {
        super();
        this.username = username;
    }

    public String toString() {
        return "UserLoginToken : '" + getUsername() + "'";
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

}
