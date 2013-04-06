package com.neodem.recipe.service.exceptions;

/**
 * @author Vincent Fumo
 * @version 1.0
 */
public class SecurityBadPasswordException extends SecurityServiceCheckedException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SecurityBadPasswordException() {
        super();
    }

    public SecurityBadPasswordException(String message) {
        super(message);
    }

    public SecurityBadPasswordException(Throwable cause) {
        super(cause);
    }

    public SecurityBadPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

}
