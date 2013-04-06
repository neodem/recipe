package com.neodem.recipe.web.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

/**
 * The form used for logging into the system
 * 
 * @author Vincent Fumo
 * @version 1.0
 */
public class LoginForm extends AbstractEJValidatorForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String username;
    String password;


    public String toString() {
        return "LoginForm : userName = " + username;
    }

    public LoginForm() {
        super();
        resetFields();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
     *      javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping mapping, HttpServletRequest req) {
        resetFields();
    }

    /**
     *  
     */
    private void resetFields() {
    	username = "";
        password = "";
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
	 * @return Returns the userName.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param userName The userName to set.
	 */
	public void setUsername(String userName) {
		this.username = userName;
	}

 
}
