package com.neodem.recipe.service.exceptions;

/**
 * @author Vincent Fumo
 * @version 1.0
 */
public class SecurityBadUsernameException extends SecurityServiceCheckedException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SecurityBadUsernameException() {
        super();
    }

    public SecurityBadUsernameException(String message) {
        super(message);
    }

    public SecurityBadUsernameException(Throwable cause) {
        super(cause);
    }

    public SecurityBadUsernameException(String message, Throwable cause) {
        super(message, cause);
    }

}
