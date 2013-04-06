package com.neodem.recipe.service.exceptions;
/**
 * @author Vincent Fumo
 * @version 1.0
 */
public class SecurityLoggedInAlreadyException extends SecurityServiceCheckedException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SecurityLoggedInAlreadyException() {
        super();
    }

    public SecurityLoggedInAlreadyException(String message) {
        super(message);
    }

    public SecurityLoggedInAlreadyException(Throwable cause) {
        super(cause);
    }

    public SecurityLoggedInAlreadyException(String message, Throwable cause) {
        super(message, cause);
    }

}
