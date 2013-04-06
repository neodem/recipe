package com.neodem.recipe.web.beans;

import java.util.Locale;

import com.neodem.common.beans.AbstractSerializedBean;

public class WebUser extends AbstractSerializedBean{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username; 
    private Locale locale;
    
    public WebUser() {
        super();
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
    /**
     * @return Returns the locale.
     */
    public Locale getLocale() {
        return locale;
    }
    /**
     * @param locale The locale to set.
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
